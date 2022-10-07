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

    @GetMapping("/listen/findListenByUserId/{userId}/{page}/{size}")
    public AjaxResult findListenByUserId(@PathVariable("userId") String id, @PathVariable("page") int page, @PathVariable("size") int size) {
        System.out.println("findListenByUserId");
        Page<Listen> byId = listenRepository.findListenByUserId(id, PageRequest.of(page, size));
        return AjaxResult.success(byId);
    }

    @GetMapping("/listen/findListenByTrackId/{trackId}/{page}/{size}")
    public AjaxResult findListenByTrackId(@PathVariable("trackId") String id, @PathVariable("page") int page, @PathVariable("size") int size) {
        System.out.println("findListenByTrackId");
        Page<Listen> byId = listenRepository.findListenByTrackId(id, PageRequest.of(page, size));
        return AjaxResult.success(byId);
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
