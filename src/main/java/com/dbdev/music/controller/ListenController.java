package com.dbdev.music.controller;

import com.dbdev.music.core.AjaxResult;
import com.dbdev.music.domain.Listen;
import com.dbdev.music.body.ListenInfo;
import com.dbdev.music.repository.ListenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
public class ListenController {

    @Autowired
    private ListenRepository listenRepository;

    @GetMapping("/listen/getAllListen")
    public AjaxResult getAllListen() {
        return AjaxResult.success(listenRepository.findAll());
    }

    @GetMapping("/listen/findListenByUserName/{userName}/{page}/{size}")
    public AjaxResult findListenByUserName(@PathVariable("userName") String name, @PathVariable("page") int page, @PathVariable("size") int size) {
        System.out.println("findListenByUserName");
        Page<Listen> byName = listenRepository.findListenByUserName("%" + name + "%", PageRequest.of(page, size));
        return AjaxResult.success(byName);
    }

    @GetMapping("/listen/findListenByTrackName/{trackName}/{page}/{size}")
    public AjaxResult findListenByTrackName(@PathVariable("trackName") String name, @PathVariable("page") int page, @PathVariable("size") int size) {
        System.out.println("findListenByTrackName");
        Page<Listen> byName = listenRepository.findListenByTrackName("%" + name + "%", PageRequest.of(page, size));
        return AjaxResult.success(byName);
    }

    @PostMapping("/listen/addListen")
    public AjaxResult addListen(@RequestBody ListenInfo info) {
        listenRepository.save(
                Listen.builder()
                        .userName(info.getUserName())
                        .trackName(info.getTrackName())
                        .latestDateTimePlayBackBegan(info.getLatestDateTimePlayBackBegan())
                        .build()
        );
        return AjaxResult.success();
    }
}
