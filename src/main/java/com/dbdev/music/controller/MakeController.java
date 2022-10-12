package com.dbdev.music.controller;

import com.dbdev.music.core.AjaxResult;
import com.dbdev.music.domain.Make;
import com.dbdev.music.body.MakeInfo;
import com.dbdev.music.repository.MakeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
public class MakeController {

    @Autowired
    private MakeRepository makeRepository;

    @GetMapping("/make/getAllMake")
    public AjaxResult getAllMake() {
        return AjaxResult.success(makeRepository.findAll());
    }

    @GetMapping("/make/findMakeByAlbumId/{albumId}/{page}/{size}")
    public AjaxResult findMakeByAlbumId(@PathVariable("albumId") Long id, @PathVariable("page") int page, @PathVariable("size") int size) {
        System.out.println("findMakeByAlbumId");
        var byId = makeRepository.findByAlbumId(id, PageRequest.of(page, size));
        return AjaxResult.success(byId);
    }

    @GetMapping("/make/findMakeByArtistId/{artistId}/{page}/{size}")
    public AjaxResult findMakeByArtistId(@PathVariable("artistId") Long id, @PathVariable("page") int page, @PathVariable("size") int size) {
        System.out.println("findMakeByArtistId");
        var byId = makeRepository.findByArtistId(id, PageRequest.of(page, size));
        return AjaxResult.success(byId);
    }

    @PostMapping("/make/addMake")
    public AjaxResult addMake(@RequestBody MakeInfo info) {
        makeRepository.save(
                Make.builder()
                        .albumId(info.getAlbumId())
                        .artistId(info.getArtistId())
                        .year(info.getYear())
                        .build()
        );
        return AjaxResult.success();
    }
}
