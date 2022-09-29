package com.dbdev.music.controller;

import com.dbdev.music.core.AjaxResult;
import com.dbdev.music.domain.Artist;
import com.dbdev.music.body.ArtistInfo;
import com.dbdev.music.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ArtistController {

    @Autowired
    private ArtistRepository artistRepository;

    @GetMapping("/artist/getAllArtist")
    public AjaxResult getAllArtist() {
        return AjaxResult.success(artistRepository.findAll());
    }

    @PostMapping("/artist/addArtist")
    public AjaxResult addArtist(@RequestBody ArtistInfo info) {
        artistRepository.save(
                Artist.builder()
                        .name(info.getName())
                        .build()
        );
        return AjaxResult.success();
    }
}
