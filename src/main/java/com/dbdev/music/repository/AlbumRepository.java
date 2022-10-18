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

    Page<List<Album>> findByNameLike(String name, Pageable pageable);
    Album findByName(String name);

    @Query("select al from Album al join Make mk on al.id=mk.albumId where mk.artistId=?1")
    Page<List<Album>> findAlbumsByArtistId(Long artistId, Pageable pageable);


}
