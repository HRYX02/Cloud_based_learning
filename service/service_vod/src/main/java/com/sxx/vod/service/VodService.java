package com.sxx.vod.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author SHIXINXI
 * @description
 * @create 2023-07-16-18:57
 */
public interface VodService {
    String uploadVideoAlYun(MultipartFile file);
}
