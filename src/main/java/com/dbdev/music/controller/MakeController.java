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

    @GetMapping("/make/findMakeByAlbumName/{albumName}/{page}/{size}")
    public AjaxResult findMakeByAlbumName(@PathVariable("albumName") String name, @PathVariable("page") int page, @PathVariable("size") int size) {
        System.out.println("findMakeByAlbumName");
        Page<Make> byName = makeRepository.findMakeByAlbumName("%" + name + "%", PageRequest.of(page, size));
        return AjaxResult.success(byName);
    }

    @GetMapping("/make/findMakeByArtistName/{artistName}/{page}/{size}")
    public AjaxResult findMakeByArtistName(@PathVariable("artistName") String name, @PathVariable("page") int page, @PathVariable("size") int size) {
        System.out.println("findMakeByArtistName");
        Page<Make> byName = makeRepository.findMakeByArtistName("%" + name + "%", PageRequest.of(page, size));
        return AjaxResult.success(byName);
    }

    @PostMapping("/make/addMake")
    public AjaxResult addMake(@RequestBody MakeInfo info) {
        makeRepository.save(
                Make.builder()
                        .albumName(info.getAlbumName())
                        .artistName(info.getArtistName())
                        .year(info.getYear())
                        .build()
        );
        return AjaxResult.success();
    }
}
