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

    @GetMapping("/belong_to/findBelongToByTrackName/{trackName}/{page}/{size}")
    public AjaxResult findBelongToByTrackName(@PathVariable("trackName") String name, @PathVariable("page") int page, @PathVariable("size") int size) {
        System.out.println("findBelongToByTrackName");
        Page<Belong_to> byName = belongtoRepository.findBelong_toByTrackName("%" + name + "%", PageRequest.of(page, size));
        return AjaxResult.success(byName);
    }

    @GetMapping("/belong_to/findBelongToByAlbumName/{albumName}/{page}/{size}")
    public AjaxResult findBelongToByAlbumName(@PathVariable("albumName") String name, @PathVariable("page") int page, @PathVariable("size") int size) {
        System.out.println("findBelongToByAlbumName");
        Page<Belong_to> byName = belongtoRepository.findBelong_toByAlbumName("%" + name + "%", PageRequest.of(page, size));
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
