package com.dbdev.music.repository;

import com.dbdev.music.domain.Listen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ListenRepository extends JpaRepository<Listen, Long> {
    Optional<Listen> findByUserIDAndTrackID(String user_id, String track_id);
}
