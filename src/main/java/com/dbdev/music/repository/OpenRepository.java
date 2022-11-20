package com.dbdev.music.repository;

import com.dbdev.music.domain.Open;
import com.dbdev.music.domain.OpenWithExtraInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

public interface OpenRepository extends JpaRepository<Open, Long> {
    @Query("select new com.dbdev.music.domain.OpenWithExtraInfo(op, ab.name) " +
            "from Open op join Album ab on op.albumId=ab.id where op.userId=?1")
    Page<List<OpenWithExtraInfo>> findByUserId(Long userId, Pageable pageable);

    Page<List<Open>> findByAlbumId(Long albumId, Pageable pageable);

    Open findByUserIdAndAlbumId(Long userId, Long albumId);
}
