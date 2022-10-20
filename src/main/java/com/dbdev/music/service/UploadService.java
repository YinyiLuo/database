package com.dbdev.music.service;

import cn.hutool.core.util.ObjectUtil;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.mp3.MP3AudioHeader;
import org.jaudiotagger.audio.mp3.MP3File;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;
import com.dbdev.music.constant.Constants;
@Service
public class UploadService {

    public HashMap<String,Object> uploadFile(MultipartFile file) throws IOException
    {

        HashMap<String,Object> info = new HashMap<String,Object>();
        if(ObjectUtil.isEmpty(file))
        {
            System.out.println("错误!文件为空");
            info.put("status",Constants.FILE_ERROR);
            info.put("error","错误!文件为空");
            return info;
            //return Constants.FILE_ERROR;
        }
        if(!file.getContentType().equals("audio/mpeg"))
        {
            //System.out.println(file.getContentType());
            System.out.println("文件类型不为audio/mpeg");
            info.put("status",Constants.FILE_ERROR);
            info.put("error","文件类型不为audio/mpeg");
            return info;
            //return Constants.FILE_ERROR;
        }

        String uploadPath = "D:\\uploadFIle";
        File uploadDir = new File(uploadPath);
        if(!uploadDir.exists())
        {
            uploadDir.mkdir();
        }
        UUID uuid = UUID.randomUUID();
        String filename= uuid.toString();

        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        File f = new File(uploadPath+"\\"+filename+suffix);

        try {
            file.transferTo(f);
        }catch (Exception e)
        {
            e.printStackTrace();
            System.out.println("文件保存异常");
            info.put("status",Constants.FILE_ERROR);
            info.put("error","文件保存异常");
            return info;
        }
        String timeLength="";
        try {
            MP3File mp3File = (MP3File) AudioFileIO.read(f);
            MP3AudioHeader audioHeader = (MP3AudioHeader) mp3File.getAudioHeader();
            System.out.println("时长:" + Float.parseFloat(audioHeader.getTrackLength() + ""));
            timeLength = audioHeader.getTrackLength()+"";
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        System.out.println("文件保存成功");
        info.put("status",Constants.FILE_SUCCESS);
        info.put("file",uuid);

        info.put("timeLength",timeLength);
        return info;
    }

}
