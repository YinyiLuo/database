package com.dbdev.music.controller;

import com.dbdev.music.core.AjaxResult;
import com.dbdev.music.domain.Belong_to;
import com.dbdev.music.domain.Belong_toInfo;
import com.dbdev.music.repository.BelongToRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BelongToController {

    @Autowired
    private BelongToRepository belongtoRepository;

    @GetMapping("/belong_to/getAllBelongTo")
    public AjaxResult getAllBelongTo() {
        return AjaxResult.success(belongtoRepository.findAll());
    }

    @PostMapping("/belong_to/addBelongTo")
    public AjaxResult addBelongTo(@RequestBody Belong_toInfo info) {
        belongtoRepository.save(
                Belong_to.builder()
                        .trackId(info.getTrackId())
                        .albumId(info.getAlbumId())
                        .build()
        );
        return AjaxResult.success();
    }
}
