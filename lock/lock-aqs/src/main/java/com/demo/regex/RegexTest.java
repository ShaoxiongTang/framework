package com.demo.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

public class RegexTest {

	@Test
	public void testPhoneNumber(){
		Pattern p=Pattern.compile("\\(\\d{3}\\)\\d{3}-\\d{4}");
		Matcher m=p.matcher("(212)555-1212");
		while(m.find()){
			System.out.println(m.group());
		}
		System.out.println("over");
	}
	
	@Test
	public void testGroup(){
		// 按指定模式在字符串查找
		String line = "This order was placed for QT3000! OK?5550";
		String pattern = "(.*)(\\d+)(.*)";

		// 创建 Pattern 对象
		Pattern r = Pattern.compile(pattern);

		// 现在创建 matcher 对象
		Matcher m = r.matcher(line);
		while(m.find()){
			System.out.println("groupCount: "+m.groupCount());
			System.out.println("Found value: " + m.group(0) );
			System.out.println("Found value: " + m.group(1) );
			System.out.println("Found value: " + m.group(2) );
			System.out.println("Found value: " + m.group(3) );
		}
		System.out.println("over");
	}
	
	@Test
	public void testStartEnd(){
		Pattern p = Pattern.compile("\\bcat\\b");
		Matcher m = p.matcher("cat cat cat cattie cat"); // 获取 matcher 对象
		int count = 0;

		while(m.find()) {
			count++;
			System.out.println("Match number "+count);
			System.out.println("start(): "+m.start());
			System.out.println("end(): "+m.end());
		}
	}
	
	@Test
	public void testMatchesAndLookingAt(){
		Pattern pattern = Pattern.compile("foo");
		Matcher matcher = pattern.matcher("foooooooo");

	    System.out.println("lookingAt(): "+matcher.lookingAt());
	    System.out.println("matches(): "+matcher.matches());
	}
	
	@Test
	public void testGreedy(){
		String str = "aaa\"bbb\"ccc\"ddd\"eee";  
        System.out.println(str);  
        str = str.replaceAll("\"(.*)\"", "@"); //贪婪模式    aaa@eee
        System.out.println(str);  
	}
	
	@Test
	public void testReluctant(){
		String str = "aaa\"bbb\"ccc\"ddd\"eee";  
        System.out.println(str);  
        str = str.replaceAll("\"(.*?)\"", "@"); //勉强匹配模式    aaa@ccc@eee
        System.out.println(str);  
	}

	@Test
	public void testNoCapture(){
		Pattern p = Pattern.compile("(?<!c)a(\\d+)bd");
		Matcher m = p.matcher("da12bka3434bdca4343bdca234bm");
		while(m.find()){
		  System.out.println(m.group(0)); // 0组是整个表达式，看这里，并没有提炼出(?<!c)的字符 。结果 a3434bd
		  System.out.println(m.group(1)); // 我们只要捕获组1的数字即可。结果 3434
		}
		System.out.println("over");
	}
	
	@Test
	public void testNoCapture2(){
		Pattern p = Pattern.compile("(?<=c)a(\\d+)bd");
		Matcher m = p.matcher("da12bka3434bdca4343bdca234bm");
		while(m.find()){
		  System.out.println(m.group(0)); // 0组是整个表达式，看这里，并没有提炼出(?<=c)的字符 。结果 a4343bd
		  System.out.println(m.group(1)); // 我们只要捕获组1的数字即可。结果 4343
		}
		System.out.println("over");
	}
	
	@Test
	public void testNoCapture3(){
		Pattern p = Pattern.compile("(?:c)a(\\d+)bd");
		Matcher m = p.matcher("da12bka3434bdca4343bdca234bm");
		while(m.find()){
		  System.out.println(m.group(0)); // 0组是整个表达式，看这里，但提炼出(?:c)的字符 。结果 ca4343bd
		  System.out.println(m.group(1)); // 我们只要捕获组1的数字即可。结果 4343
		}
		System.out.println("over");
	}
	
	@Test
	public void testNoCapture4(){
		Pattern pattern = Pattern.compile("(?:(\\d+))?\\s?([a-zA-Z]+)?.+");
        String source = "2133 fdsdee4333";
        Matcher matcher = pattern.matcher(source);
        if(matcher.matches()){
            for(int i=0;i<=matcher.groupCount();i++){
                System.out.println("group "+i+":"+matcher.group(i));
            }
        }
        System.out.println("over");
	}
	
	@Test
	public void testNoCapture5(){
		String s = "xxyyxxxyxxyxx";  
        Pattern greedy = Pattern.compile("xx(.*)xx");  
        Pattern reluctant = Pattern.compile("xx(.*?)xx");  
        Pattern possessive = Pattern.compile("xx(.*+)xx");  
        Matcher m1 = greedy.matcher(s);   
        Matcher m2 = reluctant.matcher(s);   
        Matcher m3 = possessive.matcher(s);   
        while(m1.find()) {  
            System.out.println("greedy..." + m1.group(1));  
        } 
        System.out.println();
        while(m2.find()) {  
            System.out.println("reluctant..." + m2.group(1));  
        } 
        System.out.println();
        while(m3.find()) {  
            System.out.println("possessive..." + m3.group(1));  
        }  
	}
}
