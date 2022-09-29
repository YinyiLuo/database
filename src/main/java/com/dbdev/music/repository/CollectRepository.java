package com.dbdev.music.repository;

import com.dbdev.music.domain.Collect;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CollectRepository extends JpaRepository<Collect, Long> {
    Optional<Collect> findByUserId(String userId);

    Optional<Collect> findByAlbumId(String albumId);
}
