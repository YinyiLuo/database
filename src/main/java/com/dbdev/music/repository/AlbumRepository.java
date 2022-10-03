package com.dbdev.music.repository;

import com.dbdev.music.domain.Album;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AlbumRepository extends JpaRepository<Album, Long> {
    // Page<Album> findByName(String name, Pageable pageable);

    Page<Album> findAlbumByName(String name, Pageable pageable);
}
