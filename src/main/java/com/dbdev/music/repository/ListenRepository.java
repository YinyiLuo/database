package com.dbdev.music.repository;

import cn.hutool.core.lang.Opt;
import com.dbdev.music.domain.Listen;
import com.dbdev.music.domain.ListenWithExtraInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

public interface ListenRepository extends JpaRepository<Listen, Long> {
    @Query("select new com.dbdev.music.domain.ListenWithExtraInfo(ls, tc.name) " +
            "from Listen ls join Track tc on ls.trackId=tc.id where ls.userId=?1")
    Page<List<ListenWithExtraInfo>> findByUserId(Long userId, Pageable pageable);

    Page<List<Listen>> findByTrackId(Long trackId, Pageable pageable);

    Listen findByUserIdAndTrackId(Long userId, Long trackId);
}
