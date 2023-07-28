package com.sxx.commentservice.controller;


import com.sxx.commentservice.service.EduCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 评论 前端控制器
 * </p>
 *
 * @author SxxStar
 * @since 2023-07-28
 */
@RestController
@RequestMapping("/commentservice/comment")
public class CommentController {

    @Autowired
    private EduCommentService commentService;

}

