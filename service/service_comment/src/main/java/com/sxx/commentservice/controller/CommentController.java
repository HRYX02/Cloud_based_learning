package com.sxx.commentservice.controller;


import com.sxx.commentservice.service.EduCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * TODO 待编写
 * @author SxxStar
 * @since 2023-07-28
 */
@CrossOrigin
@RestController
@RequestMapping("/commentservice/comment")
public class CommentController {

    @Autowired
    private EduCommentService commentService;

}

