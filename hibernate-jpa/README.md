#jdbc、jdbcTemplate、hibernate、jpa、spring

面对数据库操作，现在有各种各样的配置，初学者很容易搞不清 hibernate做了哪些工作，jpa做了哪些工作，spring又做了哪些工作，spring data jpa又做了哪些工作，事务又是怎么来处理的等等一系列问题。

为了彻底搞清楚他们之间的关系，做了一下几个工程案例：

-	spring-jdbc：jdbc的原生方式开发和事务的使用

-	spring-jdbcTemplate：使用spring封装的JdbcTemplate开发和事务的使用

-	spring-hibernate-xml： hibernate的原生xml方式开发
	
-	spring-hibernate-xml-tx：hibernate的原生xml方式的事务的使用
	
-	spring-hibernate-annotation：hibernate的原生的注解方式开发
	
-	spring-hibernate-annotation-tx：hibernate的原生的注解方式的事务的使用
	
-	spring-jpa-hibernate：jpa的原生xml方式开发和事务的使用

-	spring-springjpa-hibernate:jpa的spring集成方式开发和事务的使用

-	spring-springjpa-hibernate-multDataSource：多数据源下jpa与spring集成开发和事务的使用

-	spring-data-jpa-hibernate：spring-data-jpa方式开发和事务的使用
