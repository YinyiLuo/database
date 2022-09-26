package com.dbdev.music.controller;

import com.dbdev.music.core.AjaxResult;
import com.dbdev.music.domain.Album;
import com.dbdev.music.domain.AlbumInfo;
import com.dbdev.music.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AlbumController {

    @Autowired
    private AlbumRepository albumRepository;

    @GetMapping("/album/getAllAlbum")
    public AjaxResult getAllAlbum() {
        return AjaxResult.success(albumRepository.findAll());
    }

    @PostMapping("/album/addAlbum")
    public AjaxResult addAlbum(@RequestBody AlbumInfo info) {
        albumRepository.save(
                Album.builder()
                        .name(info.getName())
                        .build()
        );
        return AjaxResult.success();
    }
}
