package com.dbdev.music.service;

import cn.hutool.core.util.ObjectUtil;
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
        if(file.getContentType().equals("mp3"))
        {
            System.out.println("文件类型不为mp3");
            info.put("status",Constants.FILE_ERROR);
            info.put("error","文件类型不为mp3");
            return info;
            //return Constants.FILE_ERROR;
        }

        UUID uuid = UUID.randomUUID();
        String filename= uuid.toString();
        String path="/resource/music/"+filename;
        File f = new File(path);

        if(!f.exists())
        {
            f.createNewFile();
        }
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
