package com.dbdev.music.controller;

import com.dbdev.music.core.AjaxResult;
import com.dbdev.music.domain.Open;
import com.dbdev.music.body.OpenInfo;
import com.dbdev.music.repository.OpenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OpenController {

    @Autowired
    private OpenRepository openRepository;

    @GetMapping("/open/getAllOpen")
    public AjaxResult getAllOpen() {
        return AjaxResult.success(openRepository.findAll());
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
