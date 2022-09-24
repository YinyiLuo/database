package com.dbdev.music.controller;

import com.dbdev.music.core.AjaxResult;
import com.dbdev.music.domain.Listen;
import com.dbdev.music.domain.ListenInfo;
import com.dbdev.music.repository.ListenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
                        .user_id(info.getUser_id())
                        .track_id(info.getTrack_id())
                        .latest_date_time_playback_began(info.getLatest_date_time_playback_began())
                        .build()
        );
        return AjaxResult.success();
    }
}
