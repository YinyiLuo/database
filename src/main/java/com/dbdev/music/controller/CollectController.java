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

    @GetMapping("/collect/findCollectByUserName/{userName}/{page}/{size}")
    public AjaxResult findCollectByUserName(@PathVariable("userName") String name, @PathVariable("page") int page, @PathVariable("size") int size) {
        System.out.println("findCollectByUserName");
        Page<Collect> byName = collectRepository.findCollectByUserName("%" + name + "%", PageRequest.of(page, size));
        return AjaxResult.success(byName);
    }

    @GetMapping("/collect/findCollectByAlbumName/{albumName}/{page}/{size}")
    public AjaxResult findCollectByAlbumName(@PathVariable("albumName") String name, @PathVariable("page") int page, @PathVariable("size") int size) {
        System.out.println("findCollectByAlbumName");
        Page<Collect> byName = collectRepository.findCollectByAlbumName("%" + name + "%", PageRequest.of(page, size));
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

    //用户可以删除自己的collect
    @PostMapping("/collect/removeCollect")
    public AjaxResult removeCollect()
    {

        return AjaxResult.success();
    }
}
