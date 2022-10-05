package com.dbdev.music.repository;

import cn.hutool.core.lang.Opt;
import com.dbdev.music.domain.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Page<Comment> findCommentByUserNameLike(String userName, Pageable pageable);
    Page<Comment> findCommentByAlbumNameLike(String albumName, Pageable pageable);
}
