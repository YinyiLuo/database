package com.dbdev.music.repository;

import cn.hutool.core.lang.Opt;
import com.dbdev.music.domain.Make;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MakeRepository extends JpaRepository<Make, Long> {
    Page<Make> findByAlbumId(Long albumId, Pageable pageable);

    Page<List<Make>> findByArtistId(Long artistId, Pageable pageable);
}
