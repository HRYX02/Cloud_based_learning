package com.sxx.cmsservice.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sxx.cmsservice.entity.CrmBanner;
import com.sxx.cmsservice.service.CrmBannerService;
import com.sxx.commonutils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author SxxStar
 * @description 后台轮播图管理接口
 * @since 2023-07-18
 */
@Api(description = "后台轮播图接口")
@CrossOrigin
@RestController
@RequestMapping("/cmsservice/bannerbackground")
public class CrmBannerBackgroundController {
    @Autowired
    private CrmBannerService crmBannerService;

    /**
     * @description 分页查询banner
     */
    @ApiOperation(value = "分页查询轮播图")
    @GetMapping("/pageBanner/{page}/{limit}")
    public R pageBanner(@PathVariable @ApiParam(name = "page",value = "当前页") long page, @PathVariable @ApiParam(name = "limit",value = "每页数量") long limit){
        Page<CrmBanner> pageInfo = new Page<>(page,limit);
        crmBannerService.page(pageInfo,null);

        return R.ok().data("item",pageInfo.getRecords()).data("total",pageInfo.getTotal());
    }

    /**
     * @description 添加banner
     */
    @PostMapping("addBanner")
    public R addBanner(@RequestBody @ApiParam(name = "crmBanner",value = "轮播图信息") CrmBanner crmBanner) {
        crmBannerService.save(crmBanner);
        return R.ok();
    }

    /**
     * @description 获取Banner
     */
    @ApiOperation(value = "获取Banner")
    @GetMapping("get/{id}")
    public R get(@PathVariable @ApiParam(name = "id",value = "轮播图ID") String id) {
        CrmBanner banner = crmBannerService.getById(id);
        return R.ok().data("item", banner);
    }

    /**
     * @description 修改Banner
     */
    @ApiOperation(value = "修改Banner")
    @PutMapping("update")
    public R updateById(@RequestBody @ApiParam(name = "crmBanner",value = "轮播图信息") CrmBanner banner) {
        crmBannerService.updateById(banner);
        return R.ok();
    }

    /**
     * @description 删除Banner
     */
    @ApiOperation(value = "删除Banner")
    @DeleteMapping("remove/{id}")
    public R remove(@PathVariable @ApiParam(name = "id",value = "轮播图ID") String id) {
        crmBannerService.removeById(id);
        return R.ok();
    }
}

