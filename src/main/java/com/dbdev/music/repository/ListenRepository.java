package com.dbdev.music.repository;

import cn.hutool.core.lang.Opt;
import com.dbdev.music.domain.Listen;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ListenRepository extends JpaRepository<Listen, Long> {
    Page<Listen> findListenByUserNameLike(String userName, Pageable pageable);

    Page<Listen> findListenByTrackNameLike(String trackName, Pageable pageable);
}
