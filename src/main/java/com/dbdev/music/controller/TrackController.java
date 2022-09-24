package com.dbdev.music.controller;

import com.dbdev.music.core.AjaxResult;
import com.dbdev.music.domain.Track;
import com.dbdev.music.domain.TrackInfo;
import com.dbdev.music.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TrackController {

    @Autowired
    private TrackRepository trackRepository;

    @GetMapping("/track/getAllTrack")
    public AjaxResult getAllTrack() {
        return AjaxResult.success(trackRepository.findAll());
    }

    @PostMapping("/track/addTrack")
    public AjaxResult addTrack(@RequestBody TrackInfo info) {
        trackRepository.save(
                Track.builder()
                        .name(info.getName())
                        .time_length(info.getTime_length())
                        .build()
        );
        return AjaxResult.success();
    }
}
