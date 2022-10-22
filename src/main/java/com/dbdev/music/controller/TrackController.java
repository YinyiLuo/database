package com.dbdev.music.controller;

import com.dbdev.music.core.AjaxResult;
import com.dbdev.music.domain.Track;
import com.dbdev.music.body.TrackInfo;
import com.dbdev.music.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.persistence.PostUpdate;
import java.util.List;
import java.util.Optional;


@RestController
public class TrackController {

    @Autowired
    private TrackRepository trackRepository;

    @GetMapping("/track/getAllTrack/{page}/{size}")
    public AjaxResult getAllTrack(@PathVariable("page") int page, @PathVariable("size") int size) {
        return AjaxResult.success(trackRepository.findAll(PageRequest.of(page, size)));
    }

    @GetMapping("/track/findTracksByNameLike/{name}/{page}/{size}")
    public AjaxResult findTracksByNameLike(@PathVariable("name") String name, @PathVariable("page") int page, @PathVariable("size") int size) {
        System.out.println("findTracksByNameLike");
        var byName = trackRepository.findByNameLike( "%" + name + "%", PageRequest.of(page, size));
        return AjaxResult.success(byName);
    }

    @GetMapping("/track/findTracksByAlbumNameLike/{name}/{page}/{size}")
    public AjaxResult findTracksByAlbumNameLike(@PathVariable("name") String name, @PathVariable("page") int page, @PathVariable("size") int size) {
        System.out.println("findTracksByAlbumNameLike");
        var byName = trackRepository.findTracksByAlbumNameLike("%" + name + "%", PageRequest.of(page, size));
        return AjaxResult.success(byName);
    }

    @GetMapping("/track/findTracksByArtistNameLike/{name}/{page}/{size}")
    public AjaxResult findTracksByArtistNameLike(@PathVariable("name") String name, @PathVariable("page") int page, @PathVariable("size") int size) {
        System.out.println("findTracksByArtistNameLike");
        var byName = trackRepository.findTracksByArtistNameLike("%" + name + "%", PageRequest.of(page, size));
        return AjaxResult.success(byName);
    }

    //管理员可以删除track
    @DeleteMapping("/track/removeTrack/{id}")
    public  AjaxResult removeTrack(@PathVariable("id") Long id)
    {
        trackRepository.deleteById(id);
        return AjaxResult.success();
    }

    //获取歌曲的url
    @GetMapping("/track/getUrl/{id}")
    public String getUrl(@PathVariable("id") Long id)
    {
        Optional<Track> byId = trackRepository.findById(id);
        Track track = byId.get();
        //路径可能会有问题
        return "/resource/music/"+track.getFile()+".mp3";
    }
}
