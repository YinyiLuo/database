package com.dbdev.music.controller;

import com.dbdev.music.core.AjaxResult;
import com.dbdev.music.domain.Comment;
import com.dbdev.music.domain.CommentInfo;
import com.dbdev.music.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CommentController {

    @Autowired
    private CommentRepository commentRepository;

    @GetMapping("/comment/getAllComment")
    public AjaxResult getAllComment() {
        return AjaxResult.success(commentRepository.findAll());
    }

    @PostMapping("/comment/addComment")
    public AjaxResult addComment(@RequestBody CommentInfo info) {
        commentRepository.save(
                Comment.builder()
                        .user_id(info.getUser_id())
                        .album_id(info.getAlbum_id())
                        .context(info.getContext())
                        .commented_time(info.getCommented_time())
                        .build()
        );
        return AjaxResult.success();
    }
}
