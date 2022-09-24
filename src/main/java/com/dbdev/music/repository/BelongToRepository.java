package com.dbdev.music.repository;

import com.dbdev.music.domain.Belong_to;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BelongToRepository extends JpaRepository<Belong_to, Long> {
    Optional<Belong_to> findByTrackID(String track_id);
}
