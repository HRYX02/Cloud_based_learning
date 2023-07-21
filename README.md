# 云上学习README

## 项目启动配置更改

### MySQL数据库名账户密码

```url: jdbc:mysql://localhost:33068/guli?serverTimezone=GMT%2B8```

### 日志保存位置

```<property name="log.path" value="D:\Project\cloud_based_learning\log" />```

## 项目添加模块
mvn install:install-file -DgroupId=com.aliyun -DartifactId=aliyun-java-vod-upload -Dversion=1.4.15 -Dpackaging=jar -Dfile=aliyun-java-vod-upload-1.4.15.jar
>  本项目使用的是```MyBatis-Plus```代码生成器生成的```entity``````mapper``````service``````serivviceImpl```以及```controller```类

运行```service/service_edu/src/test/java/com/sxx/codegenerator/CodeGenerator.java```文件，更改该文件中的

1. mvc类文件位置```gc.setOutputDir```
2. 数据库连接
    1. url```setUrl```
    2. 如果是MySQL5.7修改驱动类```setDriverName```
    3. 用户名密码```setUsername```和```setPassword```

## 项目编写问题记录

### 由于aliyun-java-vod-upload、aliyun-sdk-vod-upload和aliyun-sdk-oss 暂未开源所以只能手动安装

在jar包目录下使用cmd
```bash

```
### swagger访问地址

http://localhost/swagger-ui.html

### 统一结果

| 标识 | 含义 |
|---------|-------------|
| success | 响应是否成功      |
| code    | 响应码         |
| message | 返回消息        |
| data    | 返回数据，放在键值对中 |
```json
{
  "success": "boolean",
  "code": "Number",
  "message": "String",
  "data": "HashMap"
}
```

### 关于分页添加查询

#### 为什么可以将两种不同的参数类型传给data？

方法的重载

```java
// 底层R类还是将data()中的参数put到map中了
return R.ok().data("total",total).data("rows",records);

// 手动创建Map，将map放到data()中
Map map = new HashMap<>();
map.put("total",total);
map.put("records",records);
return R.ok().data(map);
```

#### MySQL中关于时间的查询语句

**时间需要使用引号引起来**

```mysql
SELECT * FROM edu_teacher WHERE gmt_create >= '2019-01-01 10:10:10' AND gmt_create <= '2023-12-01 10:10:10'
```

### @RequestBody注解

1. 如果使用了注解只能使用```PostMapping```提交
2. required = false 表示该值可以没有(目前测验不写也行)
3. put提交会使id出现问题，最好id单独提出来然后手动复制(目前测验没问题)

### 异常&日志处理

#### 异常
1. 创建自定义异常继承```RuntimeException```
2. 在统一异常类中添加规则
3. 执行自定义异常
#### 日志
```<property name="log.path" value="D:\Project\cloud_based_learning\log" />```
clone项目时，更改日志文件位置

### 前后端

#### 跨域问题

后端直接在controller上加```@CrossOrigin()```注解

### service-edu模块

> 后台老师、课程、轮播图管理

#### easy-excel读写

位置在```service/service_edu/src/test/java/com/sxx/codegenerator/excel```

#### MultipartFile上传文件注意问题

1. 上传图片默认参数名称为```file```
2. 上传文件默认参数名称为```multipartFile```

可以使用```@RequestParam```注解接收文件然后形参统一命名

#### List集合

List集合的赋值是**引用地址值**，即使先向list中添加一条数据，比如一个对象，随后调用该对象的```set()```方法修改该对象的值，List集合中的该条数据仍然改变

#### Invalid bound statement (not found): com.sxx.eduservice.mapper.EduCourseMapper.getPublishCourseInfo

maven加载时候把java文件夹中的```.java```文件编译如果有其他文件无法加载如```.xml```文件

##### 解决

```xml
<!-- 项目打包时会将java目录中的*.xml文件也进行打包 -->
<build>
	<resources>
        <resource>
            <directory>src/main/java</directory>
            <includes>
                <include>**/*.xml</include>
            </includes>
            <filtering>false</filtering>
        </resource>
    </resources>
</build>    
```

```yml
mybatis-plus:
  mapper-locations: classpath:com/sxx/eduservice/mapper/xml/*.xml
```



### service-oss模块

> 阿里云oss服务模块

#### 启动时出现保存

基础配置完成后启动oss出现```Failed to configure a DataSource: 'url' attribute is not specified and no embedded datasource could be configured.```
#### 解决
```@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)```启动时忽略数据源

### service-vod模块

> 阿里云视频点播服务模块

#### 上传视频Tomcat报错超过1M

> Maximum upload size exceeded; nested exception is java.lang.IllegalStateException: org.apache.tomcat.util.http.fileupload.FileUploadBase$FileSizeLimitExceededException: The field file exceeds its maximum permitted size of 1048576 bytes.

#### 解决

```yml
spring:
  servlet:
    multipart:
      # 最大上传单个文件大小：默认1MB
      max-file-size: 1024MB
      # 最大置总上传的数据大小：默认10MB
      max-request-size: 1024MB
```

### service-cms模块

> 前台首页&后台轮播图管理模块

### service-msm模块

> 阿里云短信服务模块

### service-ucenter模块

> 前台用户登录注册模块

### nginx

#### nginx代理

- vue端口:80
- nginx代理端口: 8080 
- service-edu模块端口:8081
- service-oss模块端口:8082
- service-vod模块端口:8083
- service-cms模块端口:8084
- service-msm模块端口:8085
- service-ucenter模块端口:8086

#### nginx传输文件大小限制

##### 解决

在```nginx.conf```中添加```client_max_body_size 1024m;```

### 热部署

#### IDEA

1. Compiler -> BuiId project automatically
2. Advanced Settings -> AIIow auto-make to start e ven if developed application is currently running
3. Modify options -> On 'Update' action

#### 如果多个模块都有SpringBoot启动类那么就需要给devtools不同的端口

默认端口为35729

在配置类中

```yml
spring:
	devtools:
    	livereload:
     		port: 35731
```

#### SpringBoot

```xml
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-devtools</artifactId>
   	<scope>runtime</scope>
    <optional>true</optional>
</dependency>
<build>
    <plugins>
        <plugin>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-maven-plugin</artifactId>
            <configuration>
                <fork>true</fork>
                <addResources>true</addResources>
            </configuration>
        </plugin>
    </plugins>
</build>
```

