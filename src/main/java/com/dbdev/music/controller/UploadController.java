package com.dbdev.music.controller;

import com.dbdev.music.constant.Constants;
import com.dbdev.music.core.AjaxResult;
import com.dbdev.music.domain.*;
import com.dbdev.music.repository.*;
import com.dbdev.music.service.TokenService;
import com.dbdev.music.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.couchbase.CouchbaseProperties;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;


@RestController
public class UploadController {
    @Autowired
    private UploadService uploadService;
    @Autowired
    private TrackRepository trackRepository;
    @Autowired
    private TokenService tokenService;

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private BelongToRepository belongToRepository;

    @Autowired
    private ArtistRepository artistRepository;

    @Autowired
    private MakeRepository makeRepository;

    @PostMapping("/uploadTrack")
    public AjaxResult uploadFile(HttpServletRequest request,
                                 @RequestParam("file") MultipartFile[] files,
                                 @RequestParam("trackNames") String[] trackNames,
                                 @RequestParam("albumName") String albumName,
                                 @RequestParam("description") String description) throws IOException {

        boolean check;
        String r = tokenService.getLoginUser(request).getSysUser().getRole();
        if (r.equals("ROLE_ADMIN")) {
            check = true;
        } else check = false;

        Long userId = tokenService.getLoginUser(request).getSysUser().getId();
        String username = tokenService.getLoginUser(request).getSysUser().getName();
        int i = 0;
        for (MultipartFile file : files) {
            HashMap<String, Object> info = uploadService.uploadFile(file);

            if ((int) info.get("status") == Constants.FILE_ERROR) {
                return AjaxResult.error(info.get("error").toString());
            } else {
                //查询id 如果不存在 就新建一个album
                Album album = albumRepository.findByName(albumName);
                Long aId = -1L;
                if (album == null) {
                    aId = albumRepository.save(
                            Album.builder()
                                    .name(albumName)
                                    .description(description)
                                    .checked(check)
                                    .build()
                    ).getId();
                } else {
                    aId = album.getId();
                }

                //保存track信息到数据库
                Long tId = trackRepository.save(
                        Track.builder()
                                .name(trackNames[i++])
                                .timeLength((String) info.get("timeLength"))
                                .file((UUID) info.get("file"))
                                .suffix(((String) info.get("suffix")).substring(1))
                                .build()
                ).getId();

                //添加belongTo
                belongToRepository.save(BelongTo.builder()
                        .albumId(aId)
                        .trackId(tId)
                        .build()
                );

                //添加艺术家的make信息
                //如果不存在这个艺术家则创建一个新的
                Artist byId = artistRepository.findByUserId(userId);
                Artist artist;
                if (byId != null) {
                    artist = byId;
                } else {
                    artist = artistRepository.save(
                            Artist.builder()
                                    .name(username)
                                    .userId(tokenService.getLoginUser(request).getSysUser().getId())
                                    .build()
                    );

                }
                makeRepository.save(
                        Make.builder()
                                .albumId(aId)
                                .artistId(artist.getId())
                                .year("2022")
                                .build()
                );

            }
        }
        return AjaxResult.success("文件上传成功");
    }


    @PostMapping("/uploadImg")
    public AjaxResult uploadImg(HttpServletRequest request, @RequestParam("file") MultipartFile file) throws IOException {
        //对应数据库未讨论
        uploadService.uploadImg(file);
        return AjaxResult.success();
    }

}
