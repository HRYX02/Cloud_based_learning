package com.sxx.ossservice.controller;

import com.sxx.commonutils.R;
import com.sxx.ossservice.service.OssService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author SHIXINXI
 * @description 上传头像Controller
 * @create 2023-06-08-9:24
 */

@Api(description = "头像上传")
@CrossOrigin
@RestController
@RequestMapping("/ossservice/fileoss")
public class OssController {

    @Autowired
    private OssService ossService;

    /**
     * @description 上传头像方法
     */
    @ApiOperation(value = "上传头像")
    @PostMapping
    public R uploadOssFile(@ApiParam(name = "file",value = "图片") MultipartFile file) {
        String url = ossService.uploadFileAvatar(file);
        return R.ok().data("url",url);
    }
}
