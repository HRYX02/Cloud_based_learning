package com.sxx.oss.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author SHIXINXI
 * @description
 * @create 2023-06-08-9:25
 */
public interface OssService {
    String uploadFileAvatar(MultipartFile file);
}
