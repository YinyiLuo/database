package com.dbdev.music.controller;

import com.dbdev.music.core.AjaxResult;
import com.dbdev.music.domain.Make;
import com.dbdev.music.domain.MakeInfo;
import com.dbdev.music.repository.MakeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MakeController {

    @Autowired
    private MakeRepository makeRepository;

    @GetMapping("/make/getAllMake")
    public AjaxResult getAllMake() {
        return AjaxResult.success(makeRepository.findAll());
    }

    @PostMapping("/make/addMake")
    public AjaxResult addMake(@RequestBody MakeInfo info) {
        makeRepository.save(
                Make.builder()
                        .album_id(info.getAlbum_id())
                        .artist_id(info.getArtist_id())
                        .year(info.getYear())
                        .build()
        );
        return AjaxResult.success();
    }
}
