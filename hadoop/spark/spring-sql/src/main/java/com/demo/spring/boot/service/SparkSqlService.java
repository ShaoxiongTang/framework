package com.demo.spring.boot.service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.ReentrantLock;

import com.demo.spring.boot.util.SparkSqlUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.sql.DataFrame;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.hive.HiveContext;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import scala.collection.JavaConversions;

@Service
public class SparkSqlService implements Serializable, InitializingBean{

	private static final long serialVersionUID = 1L;

	private Map<String, HiveContext> dbHiveContext = new ConcurrentHashMap<>();
	private Map<String, Boolean> isHiveContextReady = new ConcurrentHashMap<>();

	private ReentrantLock dbHiveContextLock = new ReentrantLock(true);

	private AtomicLong sqlCount = new AtomicLong(0);

	private ExecutorService executorService;

	@Value("${dbsNeedInit}")
	private String dbsNeedInit;

	@Override
	public void afterPropertiesSet() throws Exception {
		init();
	}

	public void init(){
		executorService = Executors.newFixedThreadPool(10);

		SparkConf conf = new SparkConf();
    	SparkContext sc = new SparkContext(conf);
		SparkSqlUtil.sparkContext = sc;

		if(!StringUtils.isEmpty(dbsNeedInit)){
			String[] parts = dbsNeedInit.split(",");
			for(String db : parts){
				addDbHiveContext(db);
			}
		}
	}

	public boolean isReady(String db){
		checkDbNotEmpty(db);
		if(Boolean.TRUE.equals(isHiveContextReady.get(db))){
			return true;
		}
		return false;
	}

	public Long asyncSql(String db, String sql) {
		checkDbAndSql(db, sql);
		Long sqlId = sqlCount.incrementAndGet();
		addSqlJob(sqlId, db, sql);
		return sqlId;
	}

	public List<Map<String, Object>> getData(String db, String sql){
		checkDbAndSql(db, sql);
		if(!isReady(db)){
			addDbHiveContext(db);
		}
		return doGetData(sql, dbHiveContext.get(db));

	}

	private void addDbHiveContext(String db){
		if(!StringUtils.isEmpty(db)){
			if(!isReady(db)){
				dbHiveContextLock.lock();
				try {
					if(!isReady(db)){
						isHiveContextReady.put(db, Boolean.FALSE);
						HiveContext hiveContext = new HiveContext(SparkSqlUtil.sparkContext);
						hiveContext.sql("use " + db);
						dbHiveContext.put(db, hiveContext);
						isHiveContextReady.put(db, Boolean.TRUE);
					}
				} finally {
					dbHiveContextLock.unlock();
				}
			}
		}
	}

	private void checkDbAndSql(String db, String sql){
		Assert.notNull(sql, "sql语句不能为空");
		Assert.notNull(db, "sql语句 " + sql + " 必须要给出所在db数据库");

		if(sql.trim().startsWith("use ")){
			throw new RuntimeException("不能通过sql命令来切换数据库");
		}
	}

	private void checkDbNotEmpty(String db){
		checkNotEmpty(db, "数据库名不能为空");
	}

	private void checkNotEmpty(String str, String errorMsg){
		if(StringUtils.isEmpty(str)){
			throw new RuntimeException(errorMsg);
		}
	}

	private List<Map<String, Object>> doGetData(String sql, HiveContext hiveContext){
		DataFrame df = hiveContext.sql(sql);
		StructType st = df.schema();

		final List<StructField> sfs = JavaConversions.asJavaList(st.toList());
		df.show();
		List<Map<String, Object>> javaRdd = df.toJavaRDD().map(new Function<Row, Map<String, Object>>(){

			private static final long serialVersionUID = 1L;

			@Override
			public Map<String, Object> call(Row v1) throws Exception {
				Map<String, Object> map = new HashMap<>();
				for(StructField sf : sfs){
					String name = sf.name();
					map.put(name, v1.getAs(name));
				}
				return map;
			}

		}).collect();
		return javaRdd;
	}
}
