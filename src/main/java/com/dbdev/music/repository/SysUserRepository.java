package com.dbdev.music.repository;

import com.dbdev.music.domain.SysUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SysUserRepository extends JpaRepository<SysUser, Long> {
    Optional<SysUser> findByEmail(String email);

    Page<List<SysUser>> findByNameLike(String name, Pageable pageable);
}
