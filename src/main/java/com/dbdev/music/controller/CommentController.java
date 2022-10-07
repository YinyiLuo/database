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

    @GetMapping("/comment/findCommentByUserId/{userId}/{page}/{size}")
    public AjaxResult findCommentByUserId(@PathVariable("userId") String id, @PathVariable("page") int page, @PathVariable("size") int size) {
        System.out.println("findCollectByUserId");
        Page<Comment> byId = commentRepository.findCommentByUserId(id, PageRequest.of(page, size));
        return AjaxResult.success(byId);
    }

    @GetMapping("/comment/findCommentByAlbumId/{albumId}/{page}/{size}")
    public AjaxResult findCommentByAlbumId(@PathVariable("albumId") String id, @PathVariable("page") int page, @PathVariable("size") int size) {
        System.out.println("findCommentByAlbumId");
        Page<Comment> byId = commentRepository.findCommentByAlbumId(id, PageRequest.of(page, size));
        return AjaxResult.success(byId);
    }

    @GetMapping("/comment/findCommentByUserIdAndAlbumId/{userId}/{albumId}/{page}/{size}")
    public AjaxResult findCommentByUserIdAndAlbumId(@PathVariable("userId") String u_id, @PathVariable("albumId") String a_id, @PathVariable("page") int page, @PathVariable("size") int size) {
        System.out.println("findCommentByUserIdAndAlbumId");
        Page<Comment> by2Ids = commentRepository.findCommentByUserIdAndAlbumId(u_id, a_id, PageRequest.of(page, size));
        return AjaxResult.success(by2Ids);
    }

    @PostMapping("/comment/addComment")
    public AjaxResult addComment(@RequestBody CommentInfo info) {
        commentRepository.save(
                Comment.builder()
                        .userId(info.getUserId())
                        .albumId(info.getAlbumId())
                        .context(info.getContext())
                        .commentedTime(info.getCommentedTime())
                        .build()
        );
        return AjaxResult.success();
    }

    //用户可以删除自己的comment
    @PostMapping("/comment/removeComment")
    public AjaxResult removeComment(@RequestParam("id") long id)
    {
        commentRepository.deleteById(id);
        return AjaxResult.success();
    }
}
