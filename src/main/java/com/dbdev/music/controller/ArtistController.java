package com.dbdev.music.controller;

import com.dbdev.music.core.AjaxResult;
import com.dbdev.music.domain.Artist;
import com.dbdev.music.body.ArtistInfo;
import com.dbdev.music.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
public class ArtistController {

    @Autowired
    private ArtistRepository artistRepository;

    @GetMapping("/artist/getAllArtist")
    public AjaxResult getAllArtist() {
        return AjaxResult.success(artistRepository.findAll());
    }

    @GetMapping("/artist/findArtistByName/{name}/{page}/{size}")
    public AjaxResult findArtistByName(@PathVariable("name") String name, @PathVariable("page") int page, @PathVariable("size") int size) {
        System.out.println("findArtistByName");
        Page<Artist> byName = artistRepository.findArtistByName( "%" + name + "%", PageRequest.of(page, size));
        return AjaxResult.success(byName);
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
