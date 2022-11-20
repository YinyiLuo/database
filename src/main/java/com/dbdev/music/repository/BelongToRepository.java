package com.dbdev.music.repository;

import com.dbdev.music.domain.BelongTo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BelongToRepository extends JpaRepository<BelongTo, Long> {
    BelongTo findByTrackId(Long trackId);

    Page<List<BelongTo>> findByAlbumId(Long albumId, Pageable pageable);
}
