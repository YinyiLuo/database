package com.dbdev.music.controller;

import com.dbdev.music.core.AjaxResult;
import com.dbdev.music.domain.Collect;
import com.dbdev.music.domain.CollectInfo;
import com.dbdev.music.repository.CollectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CollectController {

    @Autowired
    private CollectRepository collectRepository;

    @GetMapping("/collect/getAllCollect")
    public AjaxResult getAllCollect() {
        return AjaxResult.success(collectRepository.findAll());
    }

    @PostMapping("/collect/addCollect")
    public AjaxResult addCollect(@RequestBody CollectInfo info) {
        collectRepository.save(
                Collect.builder()
                        .user_id(info.getUser_id())
                        .album_id(info.getAlbum_id())
                        .collected_time(info.getCollected_time())
                        .build()
        );
        return AjaxResult.success();
    }
}
