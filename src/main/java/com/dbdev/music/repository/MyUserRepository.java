package com.dbdev.music.repository;

import com.dbdev.music.domain.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MyUserRepository extends JpaRepository<MyUser, Long> {
    List<MyUser> findByName(String name);
}
