Date.prototype.Format = function(fmt)   
{  
  var o = {   
    "M+" : this.getMonth()+1,                 //月份   
    "d+" : this.getDate(),                    //日   
    "h+" : this.getHours(),                   //小时   
    "m+" : this.getMinutes(),                 //分   
    "s+" : this.getSeconds(),                 //秒   
    "q+" : Math.floor((this.getMonth()+3)/3), //季度   
    "S"  : this.getMilliseconds()             //毫秒   
  };   
  if(/(y+)/.test(fmt))   
    fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));   
  for(var k in o)   
    if(new RegExp("("+ k +")").test(fmt))   
  fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));   
  return fmt;   
};
function importExcel(callback) {
	var form=document.getElementById("import_form");
	$(form).ajaxSubmit(function(data){
		if(data['status']!=0){
			alert('导入成功');
			callback();
		}else{
			alert('导入有误');
		}
	});
}
function downloadExcelLog(file_name) {
	var url='/file/download/excel?fileName='+file_name+'.txt';
	window.open(url);
}
var OAAssetsClass=['办公费','电脑','办公设备','软件','IT设备'];
var supplierTypes=['安全类','布线工程','应用系统','网络设备类','服务支持','语音通讯类','服务器类','软件类','桌面类','代理商（运营商类）','运营商'];
function getOAType(type) {
	var len=OAAssetsClass.length;
	if(type>=0 && type<len){
		return OAAssetsClass[type];
	}else{
		return OAAssetsClass[0];
	}
}
function getMapSize(map) {
	var i=0;
	for(key in map){
		i++;
	}
	return i;
}
function getUrlParam(name){
	var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
	var r = window.location.search.substr(1).match(reg);  //匹配目标参数
	if (r!=null) 
		return unescape(r[2]); 
	return '';
} 
function getDataText(time){
	var date;
	if(time==null){
		date=new Date();
	}else{
		date=createDateFromTime(time);
	}
	return date.Format("yyyy-MM-dd");
}
function getDataDetailText(time){
	var date=createDateFromTime(time);
	return date.Format("yyyy-MM-dd hh:mm:ss");
}
function ValidateNumber(e, pnumber){ 
	if (!(/^\d+$/.test(pnumber) || (/^\d+\.\d+$/.test(pnumber)))){
		alert($(e).val()+':'+pnumber);
		//$(e).val(/^\d+\.?\d+/.exec($(e).val())); 
	}
	return false; 
} 
function createDateFromTime(time){
	return new Date(time);
}
function createDateFromStr(date_str) {
	return new Date(Date.parse(date_str.replace(/-/g,"/")));   
}
function getTimeFromDate(date){
	return date.getTime();
}
function getSelectOptionById(selectId){
	var select_element=document.getElementById(selectId);
	return getSelectOption(select_element);
}
function getSelectOption(select_element){
	return select_element.options[select_element.selectedIndex];
}
function _query(url,body,callback){
	post(url,body,callback);
}
function _query_render(url,body,template,target){
	post(url,body,function(result){
		var data={'data':result};
		baiduRender(data,template,target,false);
		initjQueryTable();
	});
}
function initjQueryTable() {
			$("#approval_list").dataTable().fnDestroy();
				$("#approval_list").dataTable({
				"aLengthMenu": [[10, 25, 50, -1], [10, 25, 50, "All"]],
				"bInfo": true,
				"bDestroy":true,
				"sPaginationType": "full_numbers",
			    "oLanguage": {
                    "sProcessing": "正在加载中......",
                    "sLengthMenu": "每页显示 _MENU_ 条记录",
                    "sZeroRecords": "对不起，查询不到相关数据！",
                    "sEmptyTable": "表中无数据存在！",
                    "sInfo": "当前显示 _START_ 到 _END_ 条，共 _TOTAL_ 条记录",
                    "sInfoFiltered": "数据表中共为 _MAX_ 条记录",
                    "sSearch": "搜索"
                },
                "aoColumnDefs": [{ "bSortable": false, "aTargets": [0] }]
			});
		}
function hideElementById(id) {
	var e=document.getElementById(id);
	hideElement(e);
}
function hideElement(e) {
	e.style.display="none";
}
function _resetQueryBody(query_columns_type){
	for(key in query_columns_type){
		document.getElementById('query_'+key).value='';
	}
}
function _getQueryBody(table_name,query_columns_type){
	var status=table_name+'.status';
	var data={
			'columns':[table_name+'.*'],
			'params':{}
	};
	data.params[status]=1;
	for(key in query_columns_type){
		addLikeParams(data.params,table_name+'.'+key,'query_'+key,query_columns_type[key]);
	}
	return data;
}
function addParams(params,key,id,column_type){
	var value=document.getElementById(id).value.trim();
	if(value!=''){
		if(column_type=='int'){
			value=parseInt(value);
		}else if(column_type=='float'){
			value=parseFloat(value);
		}
		params[key]=value;
	}
}
function addLikeParams(params,key,id,column_type){
	var value=document.getElementById(id).value.trim();
	if(value!=''){
		if(column_type=='int'){
			value=parseInt(value);
		}else if(column_type=='float'){
			value=parseFloat(value);
		}else if(column_type=='string'){
			value='%'+value+'%';
		}
		params[key]=value;
	}
}
function _update(new_body,id,url,callback){
	var old_body={
			'id':parseInt(id)
	};
	var data={
			'newObj':new_body,
			'oldObj':old_body
	};
	post(url, data,callback);
}
function _updateShow(table_name,id,url,callback){
	var key=table_name+'.id';
	var data={
			'columns':[table_name+'.*'],
			'params':{}
	};
	data.params[key]=id;
	post(url, data,callback);
}
function _deleteOneById(url,id,callback){
	var data={
		'id':id
	};
	post(url, data,callback);
}
function _insert(body,url,callback){
	if(body=={}){
		alert('你没有输入内容，无法增添');
	}else{
		post(url,body,callback);
	}
}
function _setCommonInsertUpdateBody(insert,columns_type,data){
	var prefix=getPrefix(insert);
	for(key in columns_type){
		setInput(data,prefix,key);
	}
}
function _setCommonInsertUpdateBodyDiv(insert,columns_type,data){
	var prefix=getPrefix(insert);
	for(key in columns_type){
		setInputDiv(data,prefix,key);
	}
}
function setInput(data,prefix,content){
	var value='';
	if(data!=undefined && content in data){
		var tmp=data[content];
		if(tmp!=null){
			value=tmp;
		}
	}
	try{
		document.getElementById(prefix+content).value=value;
	}catch(e){
	}
}
function setInputDiv(data,prefix,content){
	var value='';
	if(data!=undefined && content in data){
		var tmp=data[content];
		if(tmp!=null){
			value=tmp;
		}
	}
	document.getElementById(prefix+content).innerHTML=value;
}
function _getCommonInsertUpdateBody(insert,columns_type){
	var prefix=getPrefix(insert);
	var body={};
	for(key in columns_type){
		joinInput(body,prefix,key,columns_type[key]);
	}
	return body;
}
function getAutoCompleteContent(result,list,key){
	for(var i=0,len=result.length;i<len;i++){
		list.push(result[i][key]);
	}
}
function joinInput(body,prefix,content,type){
	var value=document.getElementById(prefix+content).value.trim();
	if(value!=""){
		if(type=='int'){
			value=parseInt(value);
		}else if(type=='float'){
			value=parseFloat(value);
		}
		body[content]=value;
	}
}
function getPrefix(insert){
	if(!insert){
		return 'update_';
	}else{
		return 'insert_';
	}
}
function _getAll(url,table_name,template,target){
	_getAllData(url, table_name,function(result){
		var data={'data':result};
		baiduRender(data,template,target,false);
	});
}
function replaceDataValue(data,columns_process){
	var len=data.length;
	for(var i=0;i<len;i++){
		var item=data[i];
		for(column in columns_process){
			if(column in item){
				item[column]=columns_process[column](item[column]);
			}
		}
	}
}
function _getAllData(url,table_name,callback){
	var data={
			'columns':[table_name+'.*'],
			'params':{}
	};
	post(url,data,callback);
}
function baiduRenderHtml(data,template){
	var bt=baidu.template;
    var html=bt(template,data);
    return html;
}
function baiduRender(data,template,target_id,append){
    if(append==null){
        append=false;
    }
    var html=baiduRenderHtml(data, template);
    var target=document.getElementById(target_id);
    if(append){
       target.innerHTML=target.innerHTML+html;
    }else{
       target.innerHTML=html;
    }
    return html;
}
function post(url,data,callback){
	$.ajax({
        url:url,
        type:'POST',
        data:JSON.stringify(data),
        dataType:'json',
        contentType:"application/json; charset=utf-8",
        success:function(result){
            callback(result);
        }
    });
}
function isInArray(value,list){
	for(key in list){
		if(key==value){
			return true;
		}
	}
	return false;
}
function get(url,callback){
	$.ajax({
        url:url,
        type:'GET',
        dataType:'json',
        contentType:"application/json; charset=utf-8",
        success:function(result){
            callback(result);
        }
    });
}