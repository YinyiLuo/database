package com.dbdev.music.repository;

import com.dbdev.music.domain.Artist;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ArtistRepository extends JpaRepository<Artist, Long> {
    // Page<Artist> findByName(String name, Pageable pageable);

    Page<Artist> findArtistByName(String name, Pageable pageable);
}
