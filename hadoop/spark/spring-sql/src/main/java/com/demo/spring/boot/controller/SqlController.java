package com.demo.spring.boot.controller;

import com.demo.spring.boot.service.SparkSqlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by lg on 2016/5/15.
 */

@RestController
public class SqlController extends BaseController{

    @Autowired
    private SparkSqlService sparkSqlService;

    @RequestMapping("/sql/sync")
    public Object syncSql(@RequestBody Map<String, Object> data) {
        String sql = (String) data.get("sql");
        String db = (String) data.get("db");
        Assert.notNull(sql, "sql can not be null");
        return sparkSqlService.getData(db, sql);
    }

    @RequestMapping("/sql/async")
    public Object asyncSql(@RequestBody Map<String, Object> data) {
        String sql = (String) data.get("sql");
        String db = (String) data.get("db");
        Assert.notNull(sql, "sql can not be null");
        return sparkSqlService.asyncSql(db, sql);
    }

}
