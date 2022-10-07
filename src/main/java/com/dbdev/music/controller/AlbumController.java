package com.dbdev.music.controller;

import com.dbdev.music.core.AjaxResult;
import com.dbdev.music.domain.Album;
import com.dbdev.music.body.AlbumInfo;
import com.dbdev.music.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
public class AlbumController {

    @Autowired
    private AlbumRepository albumRepository;

    @GetMapping("/album/getAllAlbum")
    public AjaxResult getAllAlbum() {
        return AjaxResult.success(albumRepository.findAll());
    }

    @GetMapping("/album/findAlbumByNameLike/{name}/{page}/{size}")
    public AjaxResult findAlbumByNameLike(@PathVariable("name") String name, @PathVariable("page") int page, @PathVariable("size") int size) {
        System.out.println("findAlbumByNameLike");
        Page<Album> byName = albumRepository.findAlbumByNameLike("%" + name + "%", PageRequest.of(page, size));
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
    @PostMapping("/album/removeAlbum")
    public  AjaxResult removeAlbum(@RequestParam("id") long id)
    {
        albumRepository.deleteById(id);
        return AjaxResult.success();
    }
}
