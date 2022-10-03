package com.dbdev.music.controller;

import com.dbdev.music.core.AjaxResult;
import com.dbdev.music.domain.Open;
import com.dbdev.music.body.OpenInfo;
import com.dbdev.music.repository.OpenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
public class OpenController {

    @Autowired
    private OpenRepository openRepository;

    @GetMapping("/open/getAllOpen")
    public AjaxResult getAllOpen() {
        return AjaxResult.success(openRepository.findAll());
    }

    @GetMapping("/open/findOpenByUserName/{userName}/{page}/{size}")
    public AjaxResult findOpenByUserName(@PathVariable("userName") String name, @PathVariable("page") int page, @PathVariable("size") int size) {
        System.out.println("findCollectByUserName");
        Page<Open> byName = openRepository.findOpenByUserName("%" + name + "%", PageRequest.of(page, size));
        return AjaxResult.success(byName);
    }

    @GetMapping("/open/findOpenByAlbumName/{albumName}/{page}/{size}")
    public AjaxResult findOpenByAlbumName(@PathVariable("albumName") String name, @PathVariable("page") int page, @PathVariable("size") int size) {
        System.out.println("findCollectByAlbumName");
        Page<Open> byName = openRepository.findOpenByAlbumName("%" + name + "%", PageRequest.of(page, size));
        return AjaxResult.success(byName);
    }

    @PostMapping("/open/addOpen")
    public AjaxResult addOpen(@RequestBody OpenInfo info) {
        openRepository.save(
                Open.builder()
                        .userName(info.getUserName())
                        .albumName(info.getAlbumName())
                        .indexLastPlayedTrack(info.getIndexLastPlayedTrack())
                        .build()
        );
        return AjaxResult.success();
    }
}
