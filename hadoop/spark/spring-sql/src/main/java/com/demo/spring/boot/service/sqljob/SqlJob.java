package com.demo.spring.boot.service.sqljob;

import com.demo.spring.boot.service.SparkSqlService;

import java.util.List;
import java.util.Map;

/**
 * Created by lg on 2016/5/15.
 */
public class SqlJob implements Runnable{

    private Long sqlId;
    private String db;
    private String sql;

    private SparkSqlService sparkSqlService;

    private List<Map<String, Object>> ret;

    public SqlJob(Long sqlId, String db, String sql, SparkSqlService sparkSqlService){
        this.sqlId = sqlId;
        this.db = db;
        this.sql = sql;
        this.sparkSqlService = sparkSqlService;
    }

    @Override
    public void run() {
        sparkSqlService.getData(db, sql);
    }
}
