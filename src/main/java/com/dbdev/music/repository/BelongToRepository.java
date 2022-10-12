package com.dbdev.music.repository;

import com.dbdev.music.domain.Belong_to;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BelongToRepository extends JpaRepository<Belong_to, Long> {
    Page<Belong_to> findByTrackId(Long trackId, Pageable pageable);

    Page<List<Belong_to>> findByAlbumId(Long albumId, Pageable pageable);
}
