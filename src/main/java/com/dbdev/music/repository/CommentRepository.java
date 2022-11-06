package com.dbdev.music.repository;

import com.dbdev.music.domain.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Page<List<Comment>> findByUserId(Long userId, Pageable pageable);

    Page<List<Comment>> findByAlbumIdAndParentIdIsNull(Long albumId, Pageable pageable);

    Page<Comment> findByUserIdAndAlbumId(Long userId, Long albumId, Pageable pageable);
}
