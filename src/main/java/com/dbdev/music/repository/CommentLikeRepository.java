package com.dbdev.music.repository;

import com.dbdev.music.domain.CommentLike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentLikeRepository extends JpaRepository<CommentLike, Long> {
    // 通过用户ID和评论ID，唯一确认某点赞信息是否存在用的，所以不分页
    CommentLike findByUserIdAndCommentId(Long userId, Long commentId);
}
