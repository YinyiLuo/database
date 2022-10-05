package com.dbdev.music.controller;

import com.dbdev.music.core.AjaxResult;
import com.dbdev.music.domain.Collect;
import com.dbdev.music.body.CollectInfo;
import com.dbdev.music.repository.CollectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
public class CollectController {

    @Autowired
    private CollectRepository collectRepository;

    @GetMapping("/collect/getAllCollect")
    public AjaxResult getAllCollect() {
        return AjaxResult.success(collectRepository.findAll());
    }

    @GetMapping("/collect/findCollectByUserNameLike/{userName}/{page}/{size}")
    public AjaxResult findCollectByUserNameLike(@PathVariable("userName") String name, @PathVariable("page") int page, @PathVariable("size") int size) {
        System.out.println("findCollectByUserNameLike");
        Page<Collect> byName = collectRepository.findCollectByUserNameLike("%" + name + "%", PageRequest.of(page, size));
        return AjaxResult.success(byName);
    }

    @GetMapping("/collect/findCollectByAlbumNameLike/{albumName}/{page}/{size}")
    public AjaxResult findCollectByAlbumNameLike(@PathVariable("albumName") String name, @PathVariable("page") int page, @PathVariable("size") int size) {
        System.out.println("findCollectByAlbumNameLike");
        Page<Collect> byName = collectRepository.findCollectByAlbumNameLike("%" + name + "%", PageRequest.of(page, size));
        return AjaxResult.success(byName);
    }

    @PostMapping("/collect/addCollect")
    public AjaxResult addCollect(@RequestBody CollectInfo info) {
        collectRepository.save(
                Collect.builder()
                        .userName(info.getUserName())
                        .albumName(info.getAlbumName())
                        .collectedTime(info.getCollectedTime())
                        .build()
        );
        return AjaxResult.success();
    }
}
