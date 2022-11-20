package com.dbdev.music.repository;

import com.dbdev.music.domain.SysUser;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SysUserRepository extends JpaRepository<SysUser, Long> {
    Optional<SysUser> findByEmail(String email);

    Page<List<SysUser>> findByNameLike(String name, Pageable pageable);

    @Modifying
    @Query("update SysUser user set user.name=:name, user.email=:email where user.id=:id")
    void updateNameAndEmail(@Param("id") Long id, @Param("name") String name, @Param("email") String email);

    @Modifying
    @Query("update SysUser user set user.password=:password where user.id=:id")
    void updatePassword(@Param("id") Long id, @Param("password") String password);
}
