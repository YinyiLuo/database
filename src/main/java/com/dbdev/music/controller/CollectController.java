package com.dbdev.music.controller;

import com.dbdev.music.core.AjaxResult;
import com.dbdev.music.domain.Collect;
import com.dbdev.music.body.CollectInfo;
import com.dbdev.music.repository.CollectRepository;
import com.dbdev.music.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.http.HttpRequest;
import java.sql.Timestamp;

@RestController
public class CollectController {

    @Autowired
    private CollectRepository collectRepository;

    @Autowired
    private TokenService tokenService;

    @GetMapping("/collect/getAllCollect")
    public AjaxResult getAllCollect() {
        return AjaxResult.success(collectRepository.findAll());
    }

    @GetMapping("/collect/findCollects/{page}/{size}")
    public AjaxResult findCollects(@PathVariable("page") int page, @PathVariable("size") int size, HttpServletRequest request) {
        System.out.println("findCollects");
        var byId = collectRepository.findByUserId(tokenService
                .getLoginUser(request).getSysUser().getId(), PageRequest.of(page, size));
        return AjaxResult.success(byId);
    }

    @GetMapping("/collect/findCollectByAlbumId/{albumId}/{page}/{size}")
    public AjaxResult findCollectByAlbumId(@PathVariable("albumId") Long id, @PathVariable("page") int page, @PathVariable("size") int size) {
        System.out.println("findCollectByAlbumId");
        var byId = collectRepository.findByAlbumId(id, PageRequest.of(page, size));
        return AjaxResult.success(byId);
    }

    @GetMapping("/collect/findCollectByUserIdAndAlbumId/{userId}/{albumId}/{page}/{size}")
    public AjaxResult findCollectByUserIdAndAlbumId(@PathVariable("userId") Long u_id, @PathVariable("albumId") Long a_id, @PathVariable("page") int page, @PathVariable("size") int size) {
        System.out.println("findCollectByUserIdAndAlbumId");
        var by2Ids = collectRepository.findByUserIdAndAlbumId(u_id, a_id);
        return AjaxResult.success(by2Ids);
    }

    @PostMapping("/collect/addCollect/{albumId}")
    public AjaxResult addCollect(@PathVariable("albumId") Long albumId, HttpServletRequest request) {
        collectRepository.save(
                Collect.builder()
                        .userId(tokenService.getLoginUser(request).getSysUser().getId())
                        .albumId(albumId)
                        .collectedTime(new Timestamp(System.currentTimeMillis()))
                        .build()
        );
        return AjaxResult.success();
    }

    // 当前用户删除自己的collect
    @DeleteMapping("/collect/removeCollect/{albumId}")
    public AjaxResult removeCollect(@PathVariable("albumId") Long albumId, HttpServletRequest request)
    {
        collectRepository.deleteById(collectRepository.findByUserIdAndAlbumId(tokenService.getLoginUser(request)
                .getSysUser().getId(), albumId).getId());
        return AjaxResult.success();
    }
}
