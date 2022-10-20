package com.dbdev.music.controller;

import com.dbdev.music.core.AjaxResult;
import com.dbdev.music.domain.Track;
import com.dbdev.music.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;


@Controller
public class CheckController {

    @Autowired
    TrackRepository trackRepository;
    @PostMapping("/check/{id}/{valid}")
    public AjaxResult Check(@PathVariable("id") Long id, @PathVariable("valid") boolean valid)
    {
        Optional<Track> byId = trackRepository.findById(id);
        Track track = null;
        if(byId.isPresent())
        {
            track=byId.get();
        }
        if(valid)
        {
            try {
                track.setChecked(true);
                trackRepository.save(track);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }

        }
        else
        {
            trackRepository.deleteById(id);
        }
        return AjaxResult.success();
    }

}
