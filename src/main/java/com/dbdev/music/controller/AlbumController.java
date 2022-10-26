package com.dbdev.music.controller;

import com.dbdev.music.core.AjaxResult;
import com.dbdev.music.domain.Album;
import com.dbdev.music.body.AlbumInfo;
import com.dbdev.music.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
public class AlbumController {

    @Autowired
    private AlbumRepository albumRepository;

    @GetMapping("/album/getAllAlbum/{page}/{size}")
    public AjaxResult getAllAlbum(@PathVariable("page") int page, @PathVariable("size") int size) {
        return AjaxResult.success(albumRepository.findAll(PageRequest.of(page, size)));
    }

    @GetMapping("/album/findAllWithExtraInfo/{page}/{size}")
    public AjaxResult findAllWithExtraInfo(@PathVariable("page") int page, @PathVariable("size") int size) {
        System.out.println("findAllAlbumsWithExtraInfo");
        return AjaxResult.success(albumRepository.findAllWithExtraInfo(PageRequest.of(page, size)));
    }

    @GetMapping("/album/findWithExtraInfoByNameLike/{name}/{page}/{size}")
    public AjaxResult findWithExtraInfoByNameLike(@PathVariable("name") String name, @PathVariable("page") int page, @PathVariable("size") int size) {
        System.out.println("findAlbumsWithExtraInfoByNameLike");
        var byName = albumRepository.findWithExtraInfoByNameLike(name, PageRequest.of(page, size));
        return AjaxResult.success(byName);
    }

    @GetMapping("/album/findById/{id}")
    public AjaxResult findById(@PathVariable("id") Long id) {
        System.out.println("findAlbumById");
        var byId = albumRepository.findById(id);
        return AjaxResult.success(byId);
    }

    @GetMapping("/album/findAlbumsByNameLike/{name}/{page}/{size}")
    public AjaxResult findAlbumsByNameLike(@PathVariable("name") String name, @PathVariable("page") int page, @PathVariable("size") int size) {
        System.out.println("findAlbumsByNameLike");
        var byName = albumRepository.findByNameLike("%" + name + "%", PageRequest.of(page, size));
        return AjaxResult.success(byName);
    }

    @GetMapping("/album/findAlbumsByArtistNameLike/{name}/{page}/{size}")
    public AjaxResult findAlbumsByArtistNameLike(@PathVariable("name") String name, @PathVariable("page") int page, @PathVariable("size") int size) {
        System.out.println("findAlbumsByArtistNameLike");
        var byName = albumRepository.findAlbumsByArtistNameLike("%" + name + "%", PageRequest.of(page, size));
        return AjaxResult.success(byName);
    }

    @GetMapping("/album/findByTrackNameLike/{name}/{page}/{size}")
    public AjaxResult findByTrackNameLike(@PathVariable("name") String name, @PathVariable("page") int page, @PathVariable("size") int size) {
        System.out.println("findByTrackNameLike");
        var byName = albumRepository.findByTrackNameLike( "%" + name + "%", PageRequest.of(page, size));
        return AjaxResult.success(byName);
    }

    @GetMapping("/album/findContainedAlbumsByArtistId/{id}/{page}/{size}")
    public AjaxResult findContainedAlbumsByArtistId(@PathVariable("id") Long id, @PathVariable("page") int page, @PathVariable("size") int size) {
        System.out.println("findContainedAlbumsByArtistId");
        var byName = albumRepository.findContainedAlbumsByArtistId(id, PageRequest.of(page, size));
        return AjaxResult.success(byName);
    }

    @PostMapping("/album/addAlbum")
    public AjaxResult addAlbum(@RequestBody AlbumInfo info) {
        albumRepository.save(
                Album.builder()
                        .name(info.getName())
                        .build()
        );
        return AjaxResult.success();
    }


    //管理员可以删除album
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/album/removeAlbum/{id}")
    public  AjaxResult removeAlbum(@PathVariable("id") Long id)
    {
        albumRepository.deleteById(id);
        return AjaxResult.success();
    }
}
