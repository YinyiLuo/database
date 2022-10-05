package com.dbdev.music.repository;

import com.dbdev.music.domain.Collect;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CollectRepository extends JpaRepository<Collect, Long> {
    Page<Collect> findCollectByUserNameLike(String userName, Pageable pageable);

    Page<Collect> findCollectByAlbumNameLike(String albumName, Pageable pageable);
}
