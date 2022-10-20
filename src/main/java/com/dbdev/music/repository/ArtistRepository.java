package com.dbdev.music.repository;

import com.dbdev.music.domain.Album;
import com.dbdev.music.domain.Artist;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ArtistRepository extends JpaRepository<Artist, Long> {
    // Page<Artist> findByName(String name, Pageable pageable);

    Page<List<Artist>> findByNameLike(String name, Pageable pageable);

    @Query("select ar from Artist ar join Make mk on ar.id=mk.artistId " +
            "join Album al on al.id=mk.albumId where al.name like ?1")
    Page<Artist> findByAlbumNameLike(String name, Pageable pageable);

    @Query("select ar from Artist ar join Make mk on ar.id=mk.artistId " +
            "join Album al on al.id=mk.albumId join BelongTo bl on al.id=bl.albumId " +
            "join Track tc on tc.id=bl.trackId where tc.name like ?1")
    Page<Artist> findByTrackNameLike(String name, Pageable pageable);
}
