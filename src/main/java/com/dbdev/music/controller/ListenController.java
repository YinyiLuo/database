package com.dbdev.music.controller;

import com.dbdev.music.core.AjaxResult;
import com.dbdev.music.domain.Listen;
import com.dbdev.music.body.ListenInfo;
import com.dbdev.music.domain.ListenWithExtraInfo;
import com.dbdev.music.repository.ListenRepository;
import com.dbdev.music.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class ListenController {
    @Autowired
    private ListenRepository listenRepository;

    @Autowired
    private TokenService tokenService;

    @GetMapping("/listen/getAllListen")
    public AjaxResult getAllListen() {
        return AjaxResult.success(listenRepository.findAll());
    }

    @GetMapping("/listen/findListens/{page}/{size}")
    public AjaxResult findListens(HttpServletRequest request, @PathVariable("page") int page, @PathVariable("size") int size) {
        System.out.println("findListens");
        var byId = listenRepository.findByUserId(tokenService.getLoginUser(request)
                .getSysUser().getId(), PageRequest.of(page, size, ListenWithExtraInfo.sort));
        return AjaxResult.success(byId);
    }

    @GetMapping("/listen/findListenByTrackId/{trackId}/{page}/{size}")
    public AjaxResult findListenByTrackId(@PathVariable("trackId") Long id, @PathVariable("page") int page, @PathVariable("size") int size) {
        System.out.println("findListenByTrackId");
        var byId = listenRepository.findByTrackId(id, PageRequest.of(page, size));
        return AjaxResult.success(byId);
    }

    @PostMapping("/listen/addListen")
    public AjaxResult addListen(@RequestBody ListenInfo info) {
        listenRepository.save(
                Listen.builder()
                        .userId(info.getUserId())
                        .trackId(info.getTrackId())
                        .latestDateTimePlaybackBegan(info.getLatestDateTimePlaybackBegan())
                        .build()
        );
        return AjaxResult.success();
    }
}
