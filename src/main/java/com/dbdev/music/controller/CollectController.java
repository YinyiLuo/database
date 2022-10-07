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

    @GetMapping("/collect/findCollectByUserId/{userId}/{page}/{size}")
    public AjaxResult findCollectByUserId(@PathVariable("userId") String id, @PathVariable("page") int page, @PathVariable("size") int size) {
        System.out.println("findCollectByUserId");
        Page<Collect> byId = collectRepository.findCollectByUserId(id, PageRequest.of(page, size));
        return AjaxResult.success(byId);
    }

    @GetMapping("/collect/findCollectByAlbumId/{albumId}/{page}/{size}")
    public AjaxResult findCollectByAlbumId(@PathVariable("albumId") String id, @PathVariable("page") int page, @PathVariable("size") int size) {
        System.out.println("findCollectByAlbumId");
        Page<Collect> byId = collectRepository.findCollectByAlbumId(id, PageRequest.of(page, size));
        return AjaxResult.success(byId);
    }

    @GetMapping("/collect/findCollectByUserIdAndAlbumId/{userId}/{albumId}/{page}/{size}")
    public AjaxResult findCollectByUserIdAndAlbumId(@PathVariable("userId") String u_id, @PathVariable("albumId") String a_id, @PathVariable("page") int page, @PathVariable("size") int size) {
        System.out.println("findCollectByUserIdAndAlbumId");
        Page<Collect> by2Ids = collectRepository.findCollectByUserIdAndAlbumId(u_id, a_id, PageRequest.of(page, size));
        return AjaxResult.success(by2Ids);
    }

    @PostMapping("/collect/addCollect")
    public AjaxResult addCollect(@RequestBody CollectInfo info) {
        collectRepository.save(
                Collect.builder()
                        .userId(info.getUserId())
                        .albumId(info.getAlbumId())
                        .collectedTime(info.getCollectedTime())
                        .build()
        );
        return AjaxResult.success();
    }

    //用户可以删除自己的collect
    @PostMapping("/collect/removeCollect")
    public AjaxResult removeCollect(@RequestParam("id") long id)
    {
        collectRepository.deleteById(id);
        return AjaxResult.success();
    }
}
