package com.dbdev.music.repository;

import com.dbdev.music.domain.Collect;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CollectRepository extends JpaRepository<Collect, Long> {
    Page<Collect> findCollectByUserId(String userId, Pageable pageable);

    Page<Collect> findCollectByAlbumId(String albumId, Pageable pageable);

    Page<Collect> findCollectByUserIdAndAlbumId(String userId, String albumId, Pageable pageable);
}
