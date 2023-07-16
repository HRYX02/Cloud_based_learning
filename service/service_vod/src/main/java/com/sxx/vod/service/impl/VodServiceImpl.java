package com.sxx.vod.service.impl;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.sxx.vod.service.VodService;
import com.sxx.vod.utils.ConstantVodUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author SHIXINXI
 * @description
 * @create 2023-07-16-18:57
 */
@Slf4j
@Service
public class VodServiceImpl implements VodService {

    /**
     *  @description 上传文件到阿里云
     */
    @Override
    public String uploadVideoAlYun(MultipartFile file) {
        try {
            String accessKeyId = ConstantVodUtils.KEY_ID;
            String accessKeySecret = ConstantVodUtils.KEY_SECRET;
            // 文件原始名称
            String originalFilename = file.getOriginalFilename();
            // 阿里云显示名称
            String title = originalFilename.substring(0,originalFilename.lastIndexOf("."));
            InputStream inputStream = file.getInputStream();
            UploadStreamRequest request = new UploadStreamRequest(accessKeyId, accessKeySecret, title, originalFilename, inputStream);
            UploadVideoImpl uploader = new UploadVideoImpl();
            UploadStreamResponse response = uploader.uploadStream(request);
            String videoId = "";
            if (response.isSuccess()) {
                videoId = response.getVideoId();
                //如果设置回调URL无效，不影响视频上传，可以返回VideoId同时会返回错误码。其他情况上传失败时，VideoId为空，此时需要根据返回错误码分析具体错误原因
            } else {
                videoId = response.getVideoId();
            }
            return videoId;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
