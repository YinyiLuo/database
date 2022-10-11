package com.dbdev.music.controller;

import com.dbdev.music.core.AjaxResult;
import com.dbdev.music.domain.Track;
import com.dbdev.music.body.TrackInfo;
import com.dbdev.music.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class TrackController {

    @Autowired
    private TrackRepository trackRepository;

    @GetMapping("/track/getAllTrack")
    public AjaxResult getAllTrack() {
        return AjaxResult.success(trackRepository.findAll());
    }

    @GetMapping("/track/findTracksByNameLike/{name}/{page}/{size}")
    public AjaxResult findTracksByNameLike(@PathVariable("name") String name, @PathVariable("page") int page, @PathVariable("size") int size) {
        System.out.println("findTracksByNameLike");
        var byName = trackRepository.findByNameLike( "%" + name + "%", PageRequest.of(page, size));
        return AjaxResult.success(byName);
    }

    //管理员可以删除track
    @DeleteMapping("/track/removeTrack/{id}")
    public  AjaxResult removeTrack(@PathVariable("id") Long id)
    {
        trackRepository.deleteById(id);
        return AjaxResult.success();
    }

//    @PostMapping("/track/addTrack")
//    public AjaxResult addTrack(@RequestBody TrackInfo info) {
//
//        trackRepository.save(
//                Track.builder()
//                        .name(info.getName())
//                        .timeLength(info.getTimeLength())
//                        .build()
//        );
//        return AjaxResult.success();
//    }
}
