package com.demo.lock.hashmap;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.junit.Test;

public class ConcurrentHashMapTest {
	
	private ConcurrentHashMap<String,String> map=new ConcurrentHashMap<String,String>();
	
	HashSet ss;
	HashMap sfdd;

	@Test
	public void testPut(){
		map.put("key1","value1");
		map.get("key1");
	}
	
	public static void main(String[] args){
		Map<Integer,Student> studentMap=new HashMap<>();
        studentMap.put(1,new Student("lg1", 1));
        studentMap.put(2,new Student("lg2", 2));
        studentMap.put(3,new Student("lg3", 3));
        studentMap.put(4,new Student("lg4", 4));
        studentMap.put(5,new Student("lg5", 5));
        Set<Map.Entry<Integer, Student>> studentEntrySet=studentMap.entrySet();
        System.out.println(studentEntrySet);
	}
}
