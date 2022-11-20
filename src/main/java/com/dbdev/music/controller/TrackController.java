package com.dbdev.music.controller;

import com.dbdev.music.body.ListenInfo;
import com.dbdev.music.body.OpenInfo;
import com.dbdev.music.core.AjaxResult;
import com.dbdev.music.domain.BelongTo;
import com.dbdev.music.domain.Listen;
import com.dbdev.music.domain.SysUser;
import com.dbdev.music.domain.Track;
import com.dbdev.music.repository.*;
import com.dbdev.music.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@RestController
public class TrackController {
    @Autowired
    private TrackRepository trackRepository;

    @Autowired
    private BelongToRepository belongToRepository;

    @Autowired
    private OpenRepository openRepository;

    @Autowired
    private OpenController openController;

    @Autowired
    private ListenRepository listenRepository;

    @Autowired
    private ListenController listenController;

    @Autowired
    TokenService tokenService;

    @GetMapping("/track/getAllTrack/{page}/{size}")
    public AjaxResult getAllTrack(@PathVariable("page") int page, @PathVariable("size") int size) {
        return AjaxResult.success(trackRepository.findAll(PageRequest.of(page, size)));
    }

    @GetMapping("/track/findById/{id}")
    public AjaxResult findById(@PathVariable("id") Long id, HttpServletRequest request) {
        System.out.println("findTrackById");
        var track = trackRepository.findById(id);
        var userId = tokenService.getLoginUser(request).getSysUser().getId();
        var albumId = belongToRepository.findByTrackId(id).getAlbumId();
        var listenTemp = listenRepository.findByUserIdAndTrackId(userId, id);
        var openTemp = openRepository.findByUserIdAndAlbumId(userId, albumId);
        if (listenTemp == null)
            listenController.addListen(new ListenInfo(userId, id, new Timestamp(System.currentTimeMillis())));
        else {
            listenRepository.delete(listenTemp);
            listenTemp.setLatestDateTimePlaybackBegan(new Timestamp(System.currentTimeMillis()));
            listenRepository.save(listenTemp);
        }
        if (openTemp == null)
            openController.addOpen(new OpenInfo(userId, albumId, new Timestamp(System.currentTimeMillis())));
        else {
            openRepository.delete(openTemp);
            openTemp.setLatestDateTimePlaybackBegan(new Timestamp(System.currentTimeMillis()));
            openRepository.save(openTemp);
        }
        System.out.println(listenRepository.findByUserIdAndTrackId(userId, id).getLatestDateTimePlaybackBegan());
        System.out.println(openRepository.findByUserIdAndAlbumId(userId, albumId).getLatestDateTimePlaybackBegan());
        return AjaxResult.success(track);
    }

    @GetMapping("/track/findAllWithExtraInfo/{page}/{size}")
    public AjaxResult findAllWithExtraInfo(@PathVariable("page") int page, @PathVariable("size") int size) {
        System.out.println("findAllTracksWithExtraInfo");
        return AjaxResult.success(trackRepository.findAllWithExtraInfo(PageRequest.of(page, size)));
    }

    @GetMapping("/track/findWithExtraInfoByNameLike/{name}/{page}/{size}")
    public AjaxResult findWithExtraInfoByNameLike(@PathVariable("name") String name, @PathVariable("page") int page, @PathVariable("size") int size) {
        System.out.println("findTracksWithExtraInfoByNameLike");
        var byName = trackRepository.findWithExtraInfoByNameLike(name, PageRequest.of(page, size));
        return AjaxResult.success(byName);
    }

    @GetMapping("/track/findTracksByNameLike/{name}/{page}/{size}")
    public AjaxResult findTracksByNameLike(@PathVariable("name") String name, @PathVariable("page") int page, @PathVariable("size") int size) {
        System.out.println("findTracksByNameLike");
        var byName = trackRepository.findByNameLike("%" + name + "%", PageRequest.of(page, size));
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

    @GetMapping("/track/findContainedTracksByAlbumId/{id}/{page}/{size}")
    public AjaxResult findContainedTracksByAlbumId(@PathVariable("id") Long id, @PathVariable("page") int page, @PathVariable("size") int size) {
        System.out.println("findContainedTracksByAlbumId");
        var byName = trackRepository.findContainedTracksByAlbumId(id, PageRequest.of(page, size));
        return AjaxResult.success(byName);
    }

    @GetMapping("/track/findContainedTracksByArtistId/{id}/{page}/{size}")
    public AjaxResult findContainedTracksByArtistId(@PathVariable("id") Long id, @PathVariable("page") int page, @PathVariable("size") int size) {
        System.out.println("findContainedTracksByArtistId");
        var byName = trackRepository.findContainedTracksByArtistId(id, PageRequest.of(page, size));
        return AjaxResult.success(byName);
    }

    //管理员可以删除track
    @DeleteMapping("/track/removeTrack/{id}")
    public AjaxResult removeTrack(@PathVariable("id") Long id) {
        trackRepository.deleteById(id);
        return AjaxResult.success();
    }

    //获取歌曲文件
    @GetMapping(value = "/track/getSongFile/{uuid}", produces = {"audio/*;charset=utf8"})
    public void getSongFile(@PathVariable("uuid") UUID file, HttpServletResponse response) {
        Track track = trackRepository.findByFile(file);
        File fileDir = new File("src/main/resources/static/music");
        File[] files = fileDir.listFiles();
        assert files != null;
        for (File f : files) {
            if (f.getName().substring(0, f.getName().lastIndexOf(".")).equals(track.getFile().toString())) {
                response.setContentType("audio/*;charset=utf8");
                long start = 0L;
                long end = f.length();
                response.setHeader("access-control-expose-headers", "Content-Range");
                response.setHeader("Content-Range",
                        "bytes " + start + "-" + (start + end - 1) + "/" + end);
                response.setHeader("Content-Disposition", "attachment;fileName=" + f.getName());
                response.setHeader("Content-Length", "" + (end - start));
                try (
                        FileInputStream is = new FileInputStream(f);
                        ServletOutputStream os = response.getOutputStream();
                ) {
                    int len;
                    byte[] b = new byte[1024];
                    while ((len = is.read(b)) != -1)    os.write(b, 0, len);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
