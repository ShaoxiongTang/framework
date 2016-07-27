package com.demo.quartz.base.task;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import tools.joblog.api.LogClient;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.demo.quartz.base.util.FormatUtils;
import com.demo.quartz.base.util.JsonUtils;

@Component
public class ServerWashJob {
	
	private static final Logger logger=LoggerFactory.getLogger(ServerWashJob.class);
	
	@Value("${osg.token}")
	private String osgToken;
	@Value("${osg.url}")
	private String osgUrl;
	@Value("${odb.serverUri}")
	private String odbServerUri;
	@Value("${odb.uid}")
	private String odbUid;

	private HttpClient client=new HttpClient();
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	public void serverWash(){
		PostMethod postMethod=preparePostMethod();
		InputStream in=null;
		StringWriter writer=null;
		LogClient logClient = null;
		boolean success=true;
		try {
			logClient=new LogClient();
			client.executeMethod(postMethod);
			in=postMethod.getResponseBodyAsStream();
			writer = new StringWriter();
			IOUtils.copy(in,writer,"UTF-8");
			String res=writer.toString();
			JSONObject resBody=JSON.parseObject(res,JSONObject.class);
			Boolean status=resBody.getBoolean("status");
			if(status!=null && status.equals(Boolean.TRUE)){
				JSONArray servers=resBody.getJSONArray("data");
				doServerWash(servers);
			}else{
				success=false;
				String msg="request odb server error: "+resBody.getString("message");
				logger.error(msg);
				logClient.error(msg);
			}
		}catch (Exception e) {
			success=false;
			logErrorMsg(e.getMessage(),logClient);
		}finally{
			if(in!=null){
				try {
					in.close();
				} catch (IOException e) {
					logErrorMsg(e.getMessage(),logClient);
				}
			}
			if(writer!=null){
				try {
					writer.close();
				} catch (IOException e) {
					logErrorMsg(e.getMessage(),logClient);
				}
			}
		}
		
	}
	
	private void logErrorMsg(String msg,LogClient logClient){
		logger.error(msg);
		logClient.error(msg);
	}
	
	private void doServerWash(JSONArray servers) {
		if(servers==null){
			return;
		}
		Map<String,Map<String,Object>> cmsServers=getAllCmsServers();
		List<String> oldServerCicodes=new ArrayList<String>();
		List<Map<String,Object>> needUpdatedServers=new ArrayList<Map<String,Object>>();
		for(Object item:servers){
			JSONObject server=(JSONObject)item;
			String cicode=server.getString("CI_Code");
			if(cicode==null || cicode.trim().startsWith("Deleted_")){
				continue;
			}
			cicode=cicode.trim().toUpperCase();
			if(!FormatUtils.isCicodeValid(cicode)){
				logger.error("cicode {} is not valid,must start with SVR|VMS",cicode);
				continue;
			}
			if(oldServerCicodes.contains(cicode)){
				logger.error("cicode {} repeat",cicode);
				continue;
			}
			oldServerCicodes.add(cicode);
			Map<String,Object> cmsServer=cmsServers.get(cicode);
			if(cmsServer==null){
				logger.error("cicode {} does not exists in cms",cicode);
				continue;
			}
			
			// 检查IP是否合法
			JSONArray ips=server.getJSONArray("ip_address");
			if(ips==null){
				logger.error("cicode {} does not have ip_addresss",cicode);
			}else{
				for(Object ipItem:ips){
					String ip=(String) ipItem;
					if(!FormatUtils.isIpValid(ip)){
						logger.error("cicode {} ip_addresss {} is not valid",cicode,ip);
					}
				}
			}
			
			
			// 检查生产IP是否合法
			JSONArray productIps=server.getJSONArray("product_ip");
			if(productIps==null){
				logger.error("cicode {} does not have productIps",cicode);
			}else{
				for(Object productIpItem:productIps){
					String productIp=(String) productIpItem;
					if(!FormatUtils.isIpValid(productIp)){
						logger.error("cicode {} product_ip {} is not valid",cicode,productIp);
					}
				}
			}
			
			// 检查生产IP是否合法
			String ciProductIp=server.getString("CI_ProductIP");
			if(!FormatUtils.isIpValid(ciProductIp)){
				logger.error("cicode {} ciProductIp {} is not valid",cicode,ciProductIp);
			}
			
			String odbServerIp=null;
			if(ciProductIp!=null){
				odbServerIp=ciProductIp;
			}else if(productIps!=null && productIps.size()==1){
				odbServerIp=productIps.getString(0);
			}else if(ips!=null && ips.size()==1){
				odbServerIp=ips.getString(0);
			}
			
			String cmsIp=(String) cmsServer.get("ip");
			
			boolean needUpdated=false;
			
			boolean isProd=isProd(server.getString("CI_Status"),server.getString("CI_AssetStatus"));
			
			if(isProd|| "下线中".equals(server.getString("CI_Status"))){
				if(odbServerIp==null){
					if(cmsIp!=null){
						cmsServer.put("ip",null);
						needUpdated=true;
					}
				}else{
					if(!odbServerIp.equals(cmsIp)){
						cmsServer.put("ip",odbServerIp);
						needUpdated=true;
					}
				}
			}
			
			if(isProd && !"下线中".equals(server.getString("CI_Status"))){
				String poolDBId=(String) cmsServer.get("poolDBId");
				if(poolDBId!=null){
					logger.warn("cicode {} in poolId {} is offlined,so set poolId null",cicode,cmsServer.get("poolId"));
					cmsServer.put("poolDBId",null);
					needUpdated=true;
				}
			}
			
			if(needUpdated){
				needUpdatedServers.add(cmsServer);
			}
			
		}
		updateCmsServers(needUpdatedServers);
	}
	
	private void updateCmsServers(List<Map<String, Object>> needUpdatedServers) {
		if(needUpdatedServers==null || needUpdatedServers.size()<1){
			return;
		}
		List<Object[]> args=new ArrayList<Object[]>();
		logger.info("the servers need updated: {}",JsonUtils.fastJson(needUpdatedServers));
		for(Map<String,Object> server:needUpdatedServers){
			Object[] item=new Object[2];
			item[0]=server.get("ip");
			item[1]=server.get("poolDBId");
			item[2]=server.get("cicode");
			args.add(item);
		}
		try {
			jdbcTemplate.batchUpdate("update cms_server set ip=?,poo_id=? where ci_code=?",args);
		} catch (Exception e) {
			logger.error("batchUpdate errors :"+e.getMessage());
		}
	}

	private Map<String, Map<String, Object>> getAllCmsServers() {
		Map<String, Map<String, Object>> mapServers=new HashMap<String,Map<String,Object>>();
		List<Map<String,Object>> listServers=jdbcTemplate.queryForList("select s.ci_code as cicode,s.ip,s.fort,"
				+ "p.id as poolDBId,p.pool_id as poolId,p.name as poolName"
				+ "from cms_server s join cms_pool p on s.pool_id=p.id");
		for(Map<String,Object> server:listServers){
			String cicode=(String) server.get("cicode");
			if(StringUtils.hasLength(cicode) && !mapServers.containsKey(cicode)){
				mapServers.put(cicode,server);
			}else{
				logger.error("cicode {} is not valid or repeat",cicode);
			}
		}
		return mapServers;
	}

	private boolean isProd(String status,String assStatus){
		if("未使用".equals(status) && "上线中".equals(assStatus)){
			return false;
		}
		if("下线中".equals(status)){
			return false;
		}
		return true;
	}

	@SuppressWarnings("deprecation")
	private PostMethod preparePostMethod(){
		PostMethod postMethod=new PostMethod(osgUrl+odbServerUri);
		Map<String,Object> fullBody=new HashMap<String,Object>();
		fullBody.put("access_token",osgToken);
		
		Map<String,Object> requestBody=new HashMap<String,Object>();
		requestBody.put("uid",odbUid);
		requestBody.put("action","server_ex");
		
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("odb_status","cmdb*");
		
		requestBody.put("params",params);
		fullBody.put("request_body",requestBody);
		
		postMethod.setRequestBody(JsonUtils.fastJson(fullBody));
		return postMethod;
	}
}
