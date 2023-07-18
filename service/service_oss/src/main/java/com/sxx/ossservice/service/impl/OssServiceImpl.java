package com.sxx.ossservice.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.sxx.ossservice.service.OssService;
import com.sxx.ossservice.utils.ConstantPropertiesUtils;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.UUID;

/**
 * @author SHIXINXI
 * @description
 * @create 2023-06-08-9:25
 */

@Service
public class OssServiceImpl implements OssService {

    @Override
    public String uploadFileAvatar(MultipartFile file) {

        String endpoint = ConstantPropertiesUtils.END_POINT;
        String accessKeyId = ConstantPropertiesUtils.KEY_ID;
        String accessKeySecret = ConstantPropertiesUtils.KEY_SECRET;
        String bucketName = ConstantPropertiesUtils.BUCKET_NAME;


        try {
            // 创建OSSClient实例。
            OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
            // 获取上传文件输入流
            InputStream inputStream = file.getInputStream();
            // 获取文件名称
            String fileName = file.getOriginalFilename();
            /*
             * 文件名称修改
             * 1 在文件名称里添加随机唯一的值
             * 2 获取当前日期
             * 3 拼接
             */
            // 在文件名称里添加随机唯一的值
            String uuid = UUID.randomUUID().toString().replace("_","");
            fileName = uuid+fileName;
            // 获取当前日期
            String dataPath = new DateTime().toString("yyyy/MM/dd");
            // 拼接
            fileName = dataPath + "/" + fileName;

            /*
             * 调用oss方法实现上传
             * 第一个参数: Bucket名称
             * 第二个参数: 上传到oss文件路径和文件名称
             * 第三个参数: 上传文件输入流
             */
            ossClient.putObject(bucketName,fileName,inputStream);
            // 关闭OSSClient
            ossClient.shutdown();
            // 需要把上传到阿里云oos路径手动拼接出来
            // https://sxxstarbucket.oss-cn-beijing.aliyuncs.com/2.jpg
            String url = "https://"+bucketName+"."+endpoint+"/"+fileName;
            return url;
        } catch (Exception e) {
           return null;
        }
    }
}
