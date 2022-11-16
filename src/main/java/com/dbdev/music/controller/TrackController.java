package com.dbdev.music.controller;

import com.dbdev.music.core.AjaxResult;
import com.dbdev.music.domain.Track;
import com.dbdev.music.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.UUID;


@RestController
public class TrackController {

    @Autowired
    private TrackRepository trackRepository;

    @GetMapping("/track/getAllTrack/{page}/{size}")
    public AjaxResult getAllTrack(@PathVariable("page") int page, @PathVariable("size") int size) {
        return AjaxResult.success(trackRepository.findAll(PageRequest.of(page, size)));
    }

    @GetMapping("/track/findById/{id}")
    public AjaxResult findById(@PathVariable("id") Long id) {
        System.out.println("findTrackById");
        var byId = trackRepository.findById(id);
        return AjaxResult.success(byId);
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
                    while ((len = is.read(b)) != -1)
                        os.write(b, 0, len);
                    return;
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
            }
        }
    }
}
