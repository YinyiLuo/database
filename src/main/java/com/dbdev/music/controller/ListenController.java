package com.dbdev.music.controller;

import com.dbdev.music.core.AjaxResult;
import com.dbdev.music.domain.Listen;
import com.dbdev.music.body.ListenInfo;
import com.dbdev.music.repository.ListenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ListenController {

    @Autowired
    private ListenRepository listenRepository;

    @GetMapping("/listen/getAllListen")
    public AjaxResult getAllListen() {
        return AjaxResult.success(listenRepository.findAll());
    }

    @PostMapping("/listen/addListen")
    public AjaxResult addListen(@RequestBody ListenInfo info) {
        listenRepository.save(
                Listen.builder()
                        .userId(info.getUserId())
                        .trackId(info.getTrackId())
                        .latestDateTimePlayBackBegan(info.getLatestDateTimePlayBackBegan())
                        .build()
        );
        return AjaxResult.success();
    }
}
