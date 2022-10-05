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

    @GetMapping("/listen/findListenByUserNameLike/{userName}/{page}/{size}")
    public AjaxResult findListenByUserNameLike(@PathVariable("userName") String name, @PathVariable("page") int page, @PathVariable("size") int size) {
        System.out.println("findListenByUserNameLike");
        Page<Listen> byName = listenRepository.findListenByUserNameLike("%" + name + "%", PageRequest.of(page, size));
        return AjaxResult.success(byName);
    }

    @GetMapping("/listen/findListenByTrackNameLike/{trackName}/{page}/{size}")
    public AjaxResult findListenByTrackNameLike(@PathVariable("trackName") String name, @PathVariable("page") int page, @PathVariable("size") int size) {
        System.out.println("findListenByTrackNameLike");
        Page<Listen> byName = listenRepository.findListenByTrackNameLike("%" + name + "%", PageRequest.of(page, size));
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
