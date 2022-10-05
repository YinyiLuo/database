package com.dbdev.music.controller;

import com.dbdev.music.core.AjaxResult;
import com.dbdev.music.domain.Belong_to;
import com.dbdev.music.body.Belong_toInfo;
import com.dbdev.music.repository.BelongToRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
public class BelongToController {

    @Autowired
    private BelongToRepository belongtoRepository;

    @GetMapping("/belong_to/getAllBelongTo")
    public AjaxResult getAllBelongTo() {
        return AjaxResult.success(belongtoRepository.findAll());
    }

    @GetMapping("/belong_to/findBelongToByTrackNameLike/{trackName}/{page}/{size}")
    public AjaxResult findBelongToByTrackNameLike(@PathVariable("trackName") String name, @PathVariable("page") int page, @PathVariable("size") int size) {
        System.out.println("findBelongToByTrackNameLike");
        Page<Belong_to> byName = belongtoRepository.findBelong_toByTrackNameLike("%" + name + "%", PageRequest.of(page, size));
        return AjaxResult.success(byName);
    }

    @GetMapping("/belong_to/findBelongToByAlbumNameLike/{albumName}/{page}/{size}")
    public AjaxResult findBelongToByAlbumNameLike(@PathVariable("albumName") String name, @PathVariable("page") int page, @PathVariable("size") int size) {
        System.out.println("findBelongToByAlbumNameLike");
        Page<Belong_to> byName = belongtoRepository.findBelong_toByAlbumNameLike("%" + name + "%", PageRequest.of(page, size));
        return AjaxResult.success(byName);
    }

    @PostMapping("/belong_to/addBelongTo")
    public AjaxResult addBelongTo(@RequestBody Belong_toInfo info) {
        belongtoRepository.save(
                Belong_to.builder()
                        .trackName(info.getTrackName())
                        .albumName(info.getAlbumName())
                        .build()
        );
        return AjaxResult.success();
    }
}
