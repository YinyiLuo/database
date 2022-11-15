package com.dbdev.music.controller;

import com.dbdev.music.core.AjaxResult;
import com.dbdev.music.domain.Album;
import com.dbdev.music.body.AlbumInfo;
import com.dbdev.music.domain.Artist;
import com.dbdev.music.domain.Make;
import com.dbdev.music.repository.AlbumRepository;
import com.dbdev.music.repository.ArtistRepository;
import com.dbdev.music.repository.MakeRepository;
import com.dbdev.music.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class AlbumController {

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private MakeRepository makeRepository;

    @Autowired
    private ArtistRepository artistRepository;

    @Autowired
    private TokenService tokenService;

    @GetMapping("/album/getAllAlbum/{page}/{size}")
    public AjaxResult getAllAlbum(@PathVariable("page") int page, @PathVariable("size") int size) {
        return AjaxResult.success(albumRepository.findAll(PageRequest.of(page, size)));
    }

    @GetMapping("/album/findAllWithExtraInfo/{page}/{size}")
    public AjaxResult findAllWithExtraInfo(@PathVariable("page") int page, @PathVariable("size") int size) {
        System.out.println("findAllAlbumsWithExtraInfo");
        return AjaxResult.success(albumRepository.findAllWithExtraInfoAndCheckedIsTrue(PageRequest.of(page, size)));
    }

    @GetMapping("/album/findWithExtraInfoByNameLike/{name}/{page}/{size}")
    public AjaxResult findWithExtraInfoByNameLike(@PathVariable("name") String name, @PathVariable("page") int page, @PathVariable("size") int size) {
        System.out.println("findAlbumsWithExtraInfoByNameLike");
        var byName = albumRepository.findWithExtraInfoByNameLikeAndCheckedIsTrue(name, PageRequest.of(page, size));
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
        var byName = albumRepository.findByNameLikeAndCheckedIsTrue("%" + name + "%", PageRequest.of(page, size));
        return AjaxResult.success(byName);
    }

    @GetMapping("/album/findAlbumsByArtistNameLike/{name}/{page}/{size}")
    public AjaxResult findAlbumsByArtistNameLike(@PathVariable("name") String name, @PathVariable("page") int page, @PathVariable("size") int size) {
        System.out.println("findAlbumsByArtistNameLike");
        var byName = albumRepository.findAlbumsByArtistNameLikeAndCheckedIsTrue("%" + name + "%", PageRequest.of(page, size));
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
    public AjaxResult addAlbum(@RequestBody AlbumInfo info, HttpServletRequest request) {
        albumRepository.save(
                Album.builder()
                        .name(info.getName())
                        .description(info.getDescription())
                        .checked(info.getChecked())
                        .build()
        );
        Long aId = albumRepository.findByName(info.getName()).getId();
        Long uId = tokenService.getLoginUser(request).getSysUser().getId();
        artistRepository.save(
                Artist.builder()
                        .name(tokenService.getLoginUser(request).getSysUser().getName())
                        .build()
        );
        uId = artistRepository.findByName(tokenService.getLoginUser(request).getSysUser().getName()).getId();
        makeRepository.save(
                Make.builder()
                        .albumId(aId)
                        .artistId(uId)
                        .year("2022")
                        .build()
        );
        return AjaxResult.success(aId);
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
