package com.dbdev.music.repository;

import com.dbdev.music.domain.Open;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface OpenRepository extends JpaRepository<Open, Long> {
    Page<List<Open>> findByUserId(Long userId, Pageable pageable);

    Page<List<Open>> findByAlbumId(Long albumId, Pageable pageable);
}
