package com.dbdev.music.controller;

import com.dbdev.music.core.AjaxResult;
import com.dbdev.music.domain.Make;
import com.dbdev.music.body.MakeInfo;
import com.dbdev.music.repository.MakeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
                        .albumId(info.getAlbumId())
                        .artistId(info.getArtistId())
                        .year(info.getYear())
                        .build()
        );
        return AjaxResult.success();
    }
}
