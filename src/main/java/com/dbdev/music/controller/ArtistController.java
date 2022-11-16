package com.dbdev.music.controller;

import com.dbdev.music.core.AjaxResult;
import com.dbdev.music.domain.Artist;
import com.dbdev.music.body.ArtistInfo;
import com.dbdev.music.repository.ArtistRepository;
import org.aspectj.weaver.loadtime.Aj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
public class ArtistController {

    @Autowired
    private ArtistRepository artistRepository;

    @GetMapping("/artist/getAllArtist/{page}/{size}")
    public AjaxResult getAllArtist(@PathVariable("page") int page, @PathVariable("size") int size) {
        return AjaxResult.success(artistRepository.findAll(PageRequest.of(page, size)));
    }

    @GetMapping("/artist/findById/{id}")
    public AjaxResult findById(@PathVariable("id") Long id) {
        System.out.println("findArtistById");
        var byId = artistRepository.findById(id);
        return AjaxResult.success(byId);
    }

    @GetMapping("/artist/findAllWithExtraInfo/{page}/{size}")
    public AjaxResult findAllWithExtraInfo(@PathVariable("page") int page, @PathVariable("size") int size) {
        System.out.println("findAllArtistsWithExtraInfo");
        return AjaxResult.success(artistRepository.findAllWithExtraInfo(PageRequest.of(page, size)));
    }

    @GetMapping("/artist/findWithExtraInfoByNameLike/{name}/{page}/{size}")
    public AjaxResult findWithExtraInfoByNameLike(@PathVariable("name") String name, @PathVariable("page") int page, @PathVariable("size") int size) {
        System.out.println("findArtistsWithExtraInfoByNameLike");
        var byName = artistRepository.findWithExtraInfoByNameLike(name, PageRequest.of(page, size));
        return AjaxResult.success(byName);
    }

    @GetMapping("/artist/findArtistsByNameLike/{name}/{page}/{size}")
    public AjaxResult findArtistsByNameLike(@PathVariable("name") String name, @PathVariable("page") int page, @PathVariable("size") int size) {
        System.out.println("findArtistsByNameLike");
        var byName = artistRepository.findByNameLike( "%" + name + "%", PageRequest.of(page, size));
        return AjaxResult.success(byName);
    }

    @GetMapping("/artist/findByAlbumNameLike/{name}/{page}/{size}")
    public AjaxResult findByAlbumNameLike(@PathVariable("name") String name, @PathVariable("page") int page, @PathVariable("size") int size) {
        System.out.println("findByAlbumNameLike");
        var byName = artistRepository.findByAlbumNameLike( "%" + name + "%", PageRequest.of(page, size));
        return AjaxResult.success(byName);
    }

    @GetMapping("/artist/findByTrackNameLike/{name}/{page}/{size}")
    public AjaxResult findByTrackNameLike(@PathVariable("name") String name, @PathVariable("page") int page, @PathVariable("size") int size) {
        System.out.println("findByTrackNameLike");
        var byName = artistRepository.findByTrackNameLike( "%" + name + "%", PageRequest.of(page, size));
        return AjaxResult.success(byName);
    }

    @PostMapping("/artist/addArtist")
    public AjaxResult addArtist(@RequestBody ArtistInfo info) {
        artistRepository.save(
                Artist.builder()
                        .name(info.getName())
                        .userId(info.getUserId())
                        .build()
        );
        return AjaxResult.success();
    }

    //管理员可以删除artist
    @DeleteMapping("/artist/removeArtist/{id}")
    public AjaxResult removeArtist(@PathVariable("id") Long id)
    {
        artistRepository.deleteById(id);
        return AjaxResult.success();
    }

    @GetMapping("/artist/findByTrackId/{id}")
    public AjaxResult findByTrackId(@PathVariable("id") Long id)
    {
        return AjaxResult.success(artistRepository.findByTrackId(id));
    }
}
