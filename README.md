# 云上学习README

## 项目启动配置更改

### MySQL数据库名账户密码

```url: jdbc:mysql://localhost:33068/guli?serverTimezone=GMT%2B8```

### 日志保存位置

```<property name="log.path" value="D:\Project\cloud_based_learning\log" />```

## 项目添加模块

>  本项目使用的是```MyBatis-Plus```代码生成器生成的```entity``````mapper``````service``````serivviceImpl```以及```controller```类

运行```service/service_edu/src/test/java/com/sxx/codegenerator/CodeGenerator.java```文件，更改该文件中的

1. mvc类文件位置```gc.setOutputDir```
2. 数据库连接
    1. url```setUrl```
    2. 如果是MySQL5.7修改驱动类```setDriverName```
    3. 用户名密码```setUsername```和```setPassword```

## 项目编写问题记录

## 由于aliyun-java-vod-upload、aliyun-sdk-vod-upload和aliyun-sdk-oss 暂未开源所以只能手动安装
在jar包目录下使用cmd
```bash
mvn install:install-file -DgroupId=com.aliyun -DartifactId=aliyun-java-vod-upload -Dversion=1.4.15 -Dpackaging=jar -Dfile=aliyun-java-vod-upload-1.4.15.jar
```
## swagger访问地址
http://localhost/swagger-ui.html

## 统一结果
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

## 关于分页添加查询

### 为什么可以将两种不同的参数类型传给data？

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

### MySQL中关于时间的查询语句

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
#### 前后端
##### 跨域问题

后端直接在controller上加```@CrossOrigin()```注解

### oss模块
#### 启动时出现保存
基础配置完成后启动oss出现```Failed to configure a DataSource: 'url' attribute is not specified and no embedded datasource could be configured.```
#### 解决
```@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)```启动时忽略数据源

### nginx代理

- vue端口:80
- nginx代理端口: 8080 
- service_edu模块端口:8081
- service_oss模块端口:8082

### easy-excel读写
位置在```service/service_edu/src/test/java/com/sxx/codegenerator/excel```

### MultipartFile上传文件注意问题

1. 上传图片默认参数名称为```file```
2. 上传文件默认参数名称为```multipartFile```

可以使用```@RequestParam```注解接收文件然后形参统一命名