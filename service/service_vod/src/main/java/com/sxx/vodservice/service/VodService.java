package com.sxx.vodservice.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author SHIXINXI
 * @description
 * @create 2023-07-16-18:57
 */
public interface VodService {
    String uploadVideoAlYun(MultipartFile file);

    void removeVideoAlYun(String id);

    void removeAlYunVideoList(List<String> videoIdList);

    String getAlYunPlayAuth(String id);
}
