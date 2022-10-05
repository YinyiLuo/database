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

    @GetMapping("/comment/findCommentByUserNameLike/{userName}/{page}/{size}")
    public AjaxResult findCommentByUserNameLike(@PathVariable("userName") String name, @PathVariable("page") int page, @PathVariable("size") int size) {
        System.out.println("findCollectByUserNameLike");
        Page<Comment> byName = commentRepository.findCommentByUserNameLike("%" + name + "%", PageRequest.of(page, size));
        return AjaxResult.success(byName);
    }

    @GetMapping("/comment/findCommentByAlbumNameLike/{albumName}/{page}/{size}")
    public AjaxResult findCommentByAlbumNameLike(@PathVariable("albumName") String name, @PathVariable("page") int page, @PathVariable("size") int size) {
        System.out.println("findCommentByAlbumNameLike");
        Page<Comment> byName = commentRepository.findCommentByAlbumNameLike("%" + name + "%", PageRequest.of(page, size));
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
