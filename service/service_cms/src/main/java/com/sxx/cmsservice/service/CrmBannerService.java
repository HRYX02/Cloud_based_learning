package com.sxx.cmsservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sxx.cmsservice.entity.CrmBanner;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务类
 * </p>
 *
 * @author SxxStar
 * @since 2023-07-18
 */
public interface CrmBannerService extends IService<CrmBanner> {
    public List<CrmBanner> selectAllBanner();
}
