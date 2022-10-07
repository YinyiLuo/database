package com.dbdev.music.repository;

import com.dbdev.music.domain.Open;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface OpenRepository extends JpaRepository<Open, Long> {
    Page<Open> findOpenByUserId(String userId, Pageable pageable);

    Page<Open> findOpenByAlbumId(String albumId, Pageable pageable);
}
