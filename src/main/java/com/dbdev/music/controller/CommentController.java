package com.dbdev.music.controller;

import com.dbdev.music.core.AjaxResult;
import com.dbdev.music.domain.Comment;
import com.dbdev.music.body.CommentInfo;
import com.dbdev.music.repository.CommentRepository;
import com.dbdev.music.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
public class CommentController {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private CommentService commentService;

    //返回结果如何使用
    //主要是child如何使用
    @GetMapping("/getCommentsByAlbumId/{albumId}/{page}/{size}")
    public AjaxResult getComments(@PathVariable("albumId") Long albumId, @PathVariable("page") int page, @PathVariable("size") int size)
    {
        return AjaxResult.success(commentService.getComments(albumId,PageRequest.of(page, size)));
    }

    @GetMapping("/comment/getAllComment")
    public AjaxResult getAllComment() {
        return AjaxResult.success(commentRepository.findAll());
    }

    @GetMapping("/comment/findCommentByUserId/{userId}/{page}/{size}")
    public AjaxResult findCommentByUserId(@PathVariable("userId") Long id, @PathVariable("page") int page, @PathVariable("size") int size) {
        System.out.println("findCollectByUserId");
        var byId = commentRepository.findByUserId(id, PageRequest.of(page, size));
        return AjaxResult.success(byId);
    }

    //这个接口可能被废弃
    @GetMapping("/comment/findCommentByAlbumId/{albumId}/{page}/{size}")
    public AjaxResult findCommentByAlbumId(@PathVariable("albumId") Long id, @PathVariable("page") int page, @PathVariable("size") int size) {
        System.out.println("findCommentByAlbumId");
        var byId = commentRepository.findByAlbumId(id, PageRequest.of(page, size));
        return AjaxResult.success(byId);
    }

    @GetMapping("/comment/findCommentByUserIdAndAlbumId/{userId}/{albumId}/{page}/{size}")
    public AjaxResult findCommentByUserIdAndAlbumId(@PathVariable("userId") Long u_id, @PathVariable("albumId") Long a_id, @PathVariable("page") int page, @PathVariable("size") int size) {
        System.out.println("findCommentByUserIdAndAlbumId");
        Page<Comment> by2Ids = commentRepository.findByUserIdAndAlbumId(u_id, a_id, PageRequest.of(page, size));
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
    @DeleteMapping("/comment/removeComment/{id}")
    public AjaxResult removeComment(@PathVariable("id") Long id)
    {
        commentRepository.deleteById(id);
        return AjaxResult.success();
    }
}
