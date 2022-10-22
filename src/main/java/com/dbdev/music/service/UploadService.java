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
        //利用一个hash表来存一些想要的信息
        HashMap<String,Object> info = new HashMap<String,Object>();
        if(ObjectUtil.isEmpty(file))
        {
            System.out.println("错误!文件为空");
            info.put("status",Constants.FILE_ERROR);
            info.put("error","错误!文件为空");
            return info;
        }

        if(!file.getContentType().equals("audio/mpeg"))
        {
            System.out.println("文件类型不为audio/mpeg");
            info.put("status",Constants.FILE_ERROR);
            info.put("error","文件类型不为audio/mpeg");
            return info;
        }

        //创建目录
        String uploadPath = "src/main/resources/static/music";
        File uploadDir = new File(uploadPath);
        if(!uploadDir.exists())
        {
            uploadDir.mkdir();
        }

        //创建唯一的文件名
        UUID uuid = UUID.randomUUID();
        String filename= uuid.toString();
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));

        File f = new File(uploadPath+"\\"+filename+suffix);
        //保存文件
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
        //计算长度
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


    //这个要创建对应数据库，需要讨论一下
    public HashMap<String,Object> uploadImg(MultipartFile file) throws IOException
    {
        HashMap<String,Object> info = new HashMap<String,Object>();
        if(ObjectUtil.isEmpty(file))
        {
            System.out.println("错误!文件为空");
            info.put("status",Constants.FILE_ERROR);
            info.put("error","错误!文件为空");
            return info;
        }
        //创建目录
        String uploadPath = "src/main/resources/static/img";
        File uploadDir = new File(uploadPath);
        if(!uploadDir.exists())
        {
            uploadDir.mkdir();
        }

        //创建唯一的文件名
        UUID uuid = UUID.randomUUID();
        String filename= uuid.toString();
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));

        File f = new File(uploadPath+"\\"+filename+suffix);
        //保存文件
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

        System.out.println("文件保存成功");
        info.put("status",Constants.FILE_SUCCESS);
        info.put("file",uuid);
        return info;
    }

}
