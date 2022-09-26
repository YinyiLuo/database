package com.dbdev.music.repository;

import com.dbdev.music.domain.Open;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface OpenRepository extends JpaRepository<Open, Long> {
    Optional<Open> findByUserIDAndAlbumID(String user_id, String album_id);
}
