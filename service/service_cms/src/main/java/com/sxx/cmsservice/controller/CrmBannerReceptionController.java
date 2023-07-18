package com.sxx.cmsservice.controller;


import com.sxx.cmsservice.entity.CrmBanner;
import com.sxx.cmsservice.service.CrmBannerService;
import com.sxx.commonutils.R;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author SxxStar
 * @description 前台轮播图接口
 * @since 2023-07-18
 */
@Api(description = "前台轮播图接口")
@CrossOrigin
@RestController
@RequestMapping("/cmsservice/reception")
public class CrmBannerReceptionController {

    @Autowired
    private CrmBannerService bannerService;

    //查询所有banner
    @GetMapping("getAllBanner")
    public R getAllBanner() {
        List<CrmBanner> list = bannerService.list(null);
        return R.ok().data("list",list);
    }

}

