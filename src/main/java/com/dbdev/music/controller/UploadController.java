package com.dbdev.music.controller;

import com.dbdev.music.constant.Constants;
import com.dbdev.music.core.AjaxResult;
import com.dbdev.music.domain.Album;
import com.dbdev.music.domain.Track;
import com.dbdev.music.repository.AlbumRepository;
import com.dbdev.music.repository.BelongToRepository;
import com.dbdev.music.repository.TrackRepository;
import com.dbdev.music.service.TokenService;
import com.dbdev.music.service.UploadService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;


@RestController
public class UploadController {
    @Autowired
    UploadService uploadService;

    @Autowired
    TrackRepository trackRepository;
    @Autowired
    private TokenService tokenService;

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private BelongToRepository belongToRepository;
    @PostMapping("/uploadFile")
    public AjaxResult uploadFile(HttpServletRequest request, @RequestParam("file") MultipartFile file,@RequestParam("trackName") String trackName,@RequestParam("albumName") String albumName) throws IOException
    {
        System.out.println("hello");
        boolean checked;
        String r = tokenService.getLoginUser(request).getSysUser().getRole();
        if(r.equals("ROLE_ADMIN"))
        {
            checked=true;
        }
        else checked = false;

        HashMap<String,Object> info = uploadService.uploadFile(file);

        if((int) info.get("status") == Constants.FILE_ERROR)
        {
            return AjaxResult.error(info.get("error").toString());
        }
        else
        {
            //查询id 如果不存在 就新建一个album
            //Album album = albumRepository.findByName(albumName);
//            if(album==null)
//            {
//                albumRepository.save(
//                        Album.builder().build()
//                );
//            }
        trackRepository.save(
                    Track.builder()
                            .name(trackName)
                            .checked(checked)
                            .timeLength((String)info.get("timeLength"))
                            .file((UUID) info.get("file"))
                            .build()
            );
            //添加belongto


            return AjaxResult.success("文件上传成功");
        }

    }
}
