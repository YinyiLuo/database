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

    @GetMapping("/open/findOpenByUserNameLike/{userName}/{page}/{size}")
    public AjaxResult findOpenByUserNameLike(@PathVariable("userName") String name, @PathVariable("page") int page, @PathVariable("size") int size) {
        System.out.println("findCollectByUserNameLike");
        Page<Open> byName = openRepository.findOpenByUserNameLike("%" + name + "%", PageRequest.of(page, size));
        return AjaxResult.success(byName);
    }

    @GetMapping("/open/findOpenByAlbumNameLike/{albumName}/{page}/{size}")
    public AjaxResult findOpenByAlbumNameLike(@PathVariable("albumName") String name, @PathVariable("page") int page, @PathVariable("size") int size) {
        System.out.println("findCollectByAlbumNameLike");
        Page<Open> byName = openRepository.findOpenByAlbumNameLike("%" + name + "%", PageRequest.of(page, size));
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
