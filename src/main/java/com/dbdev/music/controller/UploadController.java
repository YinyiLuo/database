package com.dbdev.music.controller;



import com.dbdev.music.body.TrackInfo;
import com.dbdev.music.constant.Constants;
import com.dbdev.music.core.AjaxResult;
import com.dbdev.music.domain.Track;
import com.dbdev.music.repository.TrackRepository;
import com.dbdev.music.service.UploadService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;


@Controller
public class UploadController {
    @Autowired
    UploadService uploadService;

    @Autowired
    TrackRepository trackRepository;
    @PostMapping("/uploadFile")
    public AjaxResult uploadFile(@RequestParam("file") MultipartFile file, @RequestBody TrackInfo trackInfo) throws IOException
    {
        boolean checked;

        if(trackInfo.getRole().equals("admin"))
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
            trackRepository.save(
                    Track.builder()
                            .name(file.getOriginalFilename())
                            .checked(checked)
                            .timeLength(trackInfo.getTimeLength())
                            .file((UUID) info.get("file"))
                            .build()
            );
            return AjaxResult.success("文件上传成功");
        }

    }
}
