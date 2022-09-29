package com.dbdev.music.repository;

import cn.hutool.core.lang.Opt;
import com.dbdev.music.domain.Make;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MakeRepository extends JpaRepository<Make, Long> {
    Optional<Make> findByAlbumId(String albumId);

    Optional<Make> findByArtistId(String artistId);
}
