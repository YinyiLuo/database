package com.dbdev.music.service;

import com.dbdev.music.domain.Comment;
import com.dbdev.music.domain.CommentLike;
import com.dbdev.music.repository.CommentRepository;
import com.dbdev.music.repository.CommentLikeRepository;
import com.dbdev.music.repository.SysUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private SysUserRepository sysUserRepository;

    @Autowired
    private CommentLikeRepository commentLikeRepository;

    @Autowired
    private TokenService tokenService;

    public Page<List<Comment>> getComments(Long albumId, Pageable pageable)
    {
        return commentRepository.findByAlbumIdAndParentIdIsNull(albumId, pageable);
    }

    @Transactional
    public void operateLike(Long comId, Long userId)  // 点赞状态反转：点赞或取消点赞
    {
        var targetComment = commentRepository.findById(comId).get();
        if (commentLikeRepository.findByUserIdAndCommentId(userId, comId) != null) {
            targetComment.setLikeCnt(targetComment.getLikeCnt() - 1);
            commentLikeRepository.deleteById(commentLikeRepository.findByUserIdAndCommentId(userId, comId).getId());
        }
        else {
            targetComment.setLikeCnt(targetComment.getLikeCnt() + 1);
            commentLikeRepository.save(CommentLike.builder().userId(userId).commentId(comId).build());
        }
        commentRepository.save(targetComment);
    }

    public boolean checkLikeFlag(Long comId, Long userId)
    {
        return commentLikeRepository.findByUserIdAndCommentId(userId, comId) != null;
    }
}
