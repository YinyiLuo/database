package com.dbdev.music.controller;

import com.dbdev.music.core.AjaxResult;
import com.dbdev.music.domain.Open;
import com.dbdev.music.body.OpenInfo;
import com.dbdev.music.domain.OpenWithExtraInfo;
import com.dbdev.music.repository.OpenRepository;
import com.dbdev.music.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class OpenController {
    @Autowired
    private OpenRepository openRepository;

    @Autowired
    private TokenService tokenService;

    @GetMapping("/open/getAllOpen")
    public AjaxResult getAllOpen() {
        return AjaxResult.success(openRepository.findAll());
    }

    @GetMapping("/open/findOpens/{page}/{size}")
    public AjaxResult findOpens(HttpServletRequest request, @PathVariable("page") int page, @PathVariable("size") int size) {
        System.out.println("findOpens");
        var byId = openRepository.findByUserId(tokenService.getLoginUser(request)
                .getSysUser().getId(), PageRequest.of(page, size, OpenWithExtraInfo.sort));
        return AjaxResult.success(byId);
    }

    @GetMapping("/open/findOpenByAlbumId/{albumId}/{page}/{size}")
    public AjaxResult findOpenByAlbumId(@PathVariable("albumId") Long id, @PathVariable("page") int page, @PathVariable("size") int size) {
        System.out.println("findOpenByAlbumId");
        var byId = openRepository.findByAlbumId(id, PageRequest.of(page, size));
        return AjaxResult.success(byId);
    }

    @PostMapping("/open/addOpen")
    public AjaxResult addOpen(@RequestBody OpenInfo info) {
        openRepository.save(
                Open.builder()
                        .userId(info.getUserId())
                        .albumId(info.getAlbumId())
                        .latestDateTimePlaybackBegan(info.getLatestDateTimePlaybackBegan())
                        .build()
        );
        return AjaxResult.success();
    }
}
