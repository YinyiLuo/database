package com.dbdev.music.service;

import com.dbdev.music.domain.Track;
import com.dbdev.music.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GetResourcesService {
    @Autowired
    TrackRepository trackRepository;
    public String musicResource(Long id)//返回url的形式
    {
        Optional<Track> byId = trackRepository.findById(id);
        try
        {
            Track track = byId.get();
            String url = "src/main/resources/static/music/"+track.getFile().toString()+".mp3";
            return url;
        }
        catch (Exception e)
        {
            System.out.println("不存在这个id对应的文件");

            e.printStackTrace();
        }
        return "error";

    }


    public String picResource()
    {
        return null;
    }

}
