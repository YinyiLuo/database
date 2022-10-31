package com.dbdev.music.controller;

import com.dbdev.music.core.AjaxResult;
import com.dbdev.music.domain.BelongTo;
import com.dbdev.music.body.Belong_toInfo;
import com.dbdev.music.repository.BelongToRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/belong_to/findBelongToByTrackId/{trackId}/{page}/{size}")
    public AjaxResult findBelongToByTrackId(@PathVariable("trackId") Long id, @PathVariable("page") int page, @PathVariable("size") int size) {
        System.out.println("findBelongToByTrackId");
        var byId = belongtoRepository.findByTrackId(id, PageRequest.of(page, size));
        return AjaxResult.success(byId);
    }

    @GetMapping("/belong_to/findBelongToByAlbumId/{albumId}/{page}/{size}")
    public AjaxResult findBelongToByAlbumId(@PathVariable("albumId") Long id, @PathVariable("page") int page, @PathVariable("size") int size) {
        System.out.println("findBelongToByAlbumId");
        var byId = belongtoRepository.findByAlbumId(id, PageRequest.of(page, size));
        return AjaxResult.success(byId);
    }

    @PostMapping("/belong_to/addBelongTo")
    public AjaxResult addBelongTo(@RequestBody Belong_toInfo info) {
        belongtoRepository.save(
                BelongTo.builder()
                        .trackId(info.getTrackId())
                        .albumId(info.getAlbumId())
                        .build()
        );
        return AjaxResult.success();
    }
}
