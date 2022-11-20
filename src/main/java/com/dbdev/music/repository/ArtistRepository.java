package com.dbdev.music.repository;

import com.dbdev.music.domain.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ArtistRepository extends JpaRepository<Artist, Long> {
     Artist findByName(String name);

    Page<List<Artist>> findByNameLike(String name, Pageable pageable);

    @Query("select ar from Artist ar join Make mk on ar.id=mk.artistId " +
            "join Album al on al.id=mk.albumId where al.name like %?1% and al.checked=true")
    Page<Artist> findByAlbumNameLike(String name, Pageable pageable);

    @Query("select ar from Artist ar join Make mk on ar.id=mk.artistId " +
            "join Album al on al.id=mk.albumId join BelongTo bl on al.id=bl.albumId " +
            "join Track tc on tc.id=bl.trackId where tc.name like %?1% and al.checked=true")
    Page<Artist> findByTrackNameLike(String name, Pageable pageable);

    @Query("select distinct new com.dbdev.music.domain.ArtistWithExtraInfo(ar, count(distinct al), count(distinct tc)) " +
            "from Artist ar join Make mk on ar.id=mk.artistId " +
            "join Album al on al.id=mk.albumId join BelongTo bl on al.id=bl.albumId " +
            "join Track tc on tc.id=bl.trackId where al.checked=true group by ar, mk, bl")
    Page<List<ArtistWithExtraInfo>> findAllWithExtraInfo(PageRequest pageRequest);

    @Query("select distinct new com.dbdev.music.domain.ArtistWithExtraInfo(ar, count(distinct al), count(distinct tc)) " +
            "from Artist ar join Make mk on ar.id=mk.artistId " +
            "join Album al on al.id=mk.albumId join BelongTo bl on al.id=bl.albumId " +
            "join Track tc on tc.id=bl.trackId where ar.name like %?1% and al.checked=true group by ar, mk, bl")
    Page<List<ArtistWithExtraInfo>> findWithExtraInfoByNameLike(String name, PageRequest pageRequest);

    @Query("select arti from Artist arti join Make mk on arti.id=mk.artistId join Album al on al.id=mk.albumId " +
            "where mk.albumId=?1 and al.checked=true")
    Artist findByAlbumId(Long albumId);

    @Query("select arti from Artist arti join Make mk on arti.id=mk.artistId join Album al on al.id=mk.albumId " +
            "join BelongTo bl on al.id=bl.albumId where bl.trackId=?1 and al.checked=true")
    Artist findByTrackId(Long trackId);

    Artist findByUserId(Long userId);
}
