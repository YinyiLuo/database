package com.dbdev.music.repository;

import com.dbdev.music.domain.Track;
import com.dbdev.music.domain.TrackWithExtraInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

public interface TrackRepository extends JpaRepository<Track, Long> {
    Track findByFile(UUID file);

    Page<List<Track>> findByNameLike(String name, Pageable pageable);

    @Query("select tc from Track tc join BelongTo bl on tc.id=bl.trackId " +
            "join Album al on al.id=bl.albumId where al.name like %?1% and al.checked=true")
    Page<List<Track>> findTracksByAlbumNameLike(String name, Pageable pageable);

    @Query("select tc from Track tc join BelongTo bl on tc.id=bl.trackId " +
            "join Album al on al.id=bl.albumId join Make mk on al.id=mk.albumId " +
            "join Artist atst on atst.id=mk.artistId where atst.name like %?1% and al.checked=true")
    Page<List<Track>> findTracksByArtistNameLike(String name, Pageable pageable);

    @Query("select distinct new com.dbdev.music.domain.TrackWithExtraInfo(tc, atst.name, al.name) " +
            "from Track tc join BelongTo bl on tc.id=bl.trackId " +
            "join Album al on al.id=bl.albumId join Make mk on al.id=mk.albumId " +
            "join Artist atst on atst.id=mk.artistId where al.checked=true")
    Page<List<TrackWithExtraInfo>> findAllWithExtraInfo(PageRequest pageRequest);

    @Query("select distinct new com.dbdev.music.domain.TrackWithExtraInfo(tc, atst.name, al.name) " +
            "from Track tc join BelongTo bl on tc.id=bl.trackId " +
            "join Album al on al.id=bl.albumId join Make mk on al.id=mk.albumId " +
            "join Artist atst on atst.id=mk.artistId where tc.name like %?1% and al.checked=true")
    Page<List<TrackWithExtraInfo>> findWithExtraInfoByNameLike(String name, PageRequest pageRequest);

    @Query("select tc from Track tc join BelongTo bl on tc.id=bl.trackId where bl.albumId=?1")
    Page<Track> findContainedTracksByAlbumId(Long id, PageRequest pageRequest);

    @Query("select tc from Track tc join BelongTo bl on tc.id=bl.trackId join Album al on al.id=bl.albumId " +
            "join Make mk on al.id=mk.albumId where mk.artistId=?1 and al.checked=true")
    Page<Track> findContainedTracksByArtistId(Long id, PageRequest pageRequest);
}
