package com.dbdev.music.controller;

import com.dbdev.music.core.AjaxResult;
import com.dbdev.music.domain.Comment;
import com.dbdev.music.body.CommentInfo;
import com.dbdev.music.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
public class CommentController {

    @Autowired
    private CommentRepository commentRepository;

    @GetMapping("/comment/getAllComment")
    public AjaxResult getAllComment() {
        return AjaxResult.success(commentRepository.findAll());
    }

    @GetMapping("/comment/findCommentByUserName/{userName}/{page}/{size}")
    public AjaxResult findCommentByUserName(@PathVariable("userName") String name, @PathVariable("page") int page, @PathVariable("size") int size) {
        System.out.println("findCollectByUserName");
        Page<Comment> byName = commentRepository.findCommentByUserName("%" + name + "%", PageRequest.of(page, size));
        return AjaxResult.success(byName);
    }

    @GetMapping("/comment/findCommentByAlbumName/{albumName}/{page}/{size}")
    public AjaxResult findCommentByAlbumName(@PathVariable("albumName") String name, @PathVariable("page") int page, @PathVariable("size") int size) {
        System.out.println("findCommentByAlbumName");
        Page<Comment> byName = commentRepository.findCommentByAlbumName("%" + name + "%", PageRequest.of(page, size));
        return AjaxResult.success(byName);
    }

    @PostMapping("/comment/addComment")
    public AjaxResult addComment(@RequestBody CommentInfo info) {
        commentRepository.save(
                Comment.builder()
                        .userName(info.getUserName())
                        .albumName(info.getAlbumName())
                        .context(info.getContext())
                        .commentedTime(info.getCommentedTime())
                        .build()
        );
        return AjaxResult.success();
    }
}
