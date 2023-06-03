# 项目编写问题记录
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