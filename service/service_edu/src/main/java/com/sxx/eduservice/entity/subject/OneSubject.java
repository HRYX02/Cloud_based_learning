package com.sxx.eduservice.entity.subject;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author SHIXINXI
 * @description 一级分类
 * @create 2023-06-09-19:26
 */

@Data
public class OneSubject {
    private String id;
    private String title;

    /**
     * @description 一个一级分类有多个二级分类
     */
    private List<TwoSubject> children = new ArrayList<>();
}
