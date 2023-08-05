package com.sxx.eduservice.entity.vo.background;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author SHIXINXI
 * @description
 * @create 2023-08-05-下午 6:54
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="EduLogin对象", description="后台用户")
public class UserVo {
    private static final long serialVersionUID = 1L;

    private String username;

    private String password;
}
