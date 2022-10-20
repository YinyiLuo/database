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

    @Query("select al from Album al join Make mk on al.id=mk.albumId " +
            "join Artist arti on arti.id=mk.artistId where arti.name like ?1")
    Page<List<Album>> findAlbumsByArtistNameLike(String name, Pageable pageable);

    @Query("select al from Album al join BelongTo bl on al.id=bl.albumId " +
            "join Track tc on tc.id=bl.trackId where tc.name like ?1")
    Page<Album> findByTrackNameLike(String name, Pageable pageable);
}
