package com.demo.quartz.base.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class JsonUtils {

	public static String fastJson(Object data){
    	return JSON.toJSONString(data,
    			SerializerFeature.DisableCircularReferenceDetect,
    			SerializerFeature.WriteMapNullValue);
    }
}
