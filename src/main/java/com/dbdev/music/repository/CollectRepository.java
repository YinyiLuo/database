package com.dbdev.music.repository;

import com.dbdev.music.domain.Collect;
import com.dbdev.music.domain.CollectWithExtraInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CollectRepository extends JpaRepository<Collect, Long> {
    @Query("select new com.dbdev.music.domain.CollectWithExtraInfo(cl, ab.name) " +
            "from Collect cl join Album ab on cl.albumId=ab.id where cl.userId=?1")
    Page<List<CollectWithExtraInfo>> findByUserId(Long userId, Pageable pageable);

    Page<List<Collect>> findByAlbumId(Long albumId, Pageable pageable);

    Collect findByUserIdAndAlbumId(Long userId, Long albumId);
}
