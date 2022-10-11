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

    @GetMapping("/open/findOpenByUserId/{userId}/{page}/{size}")
    public AjaxResult findOpenByUserId(@PathVariable("userId") Long id, @PathVariable("page") int page, @PathVariable("size") int size) {
        System.out.println("findCollectByUserId");
        var byId = openRepository.findByUserId(id, PageRequest.of(page, size));
        return AjaxResult.success(byId);
    }

    @GetMapping("/open/findOpenByAlbumId/{albumId}/{page}/{size}")
    public AjaxResult findOpenByAlbumId(@PathVariable("albumId") Long id, @PathVariable("page") int page, @PathVariable("size") int size) {
        System.out.println("findCollectByAlbumId");
        var byId = openRepository.findByAlbumId(id, PageRequest.of(page, size));
        return AjaxResult.success(byId);
    }

    @PostMapping("/open/addOpen")
    public AjaxResult addOpen(@RequestBody OpenInfo info) {
        openRepository.save(
                Open.builder()
                        .userId(info.getUserId())
                        .albumId(info.getAlbumId())
                        .indexLastPlayedTrack(info.getIndexLastPlayedTrack())
                        .build()
        );
        return AjaxResult.success();
    }
}
