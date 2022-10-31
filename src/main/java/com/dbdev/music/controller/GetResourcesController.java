package com.dbdev.music.controller;

import com.dbdev.music.core.AjaxResult;
import com.dbdev.music.service.GetResourcesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GetResourcesController {
    @Autowired
    private GetResourcesService getResourcesService;

    @GetMapping("/getMusic")
    public AjaxResult getMusic(Long id)
    {
        String url = getResourcesService.musicResource(id);
        if(url.equals("error"))
        {
            return AjaxResult.error();
        }
        return AjaxResult.success(url);
    }

    //没讨论完
    @GetMapping("/getImg")
    public AjaxResult getImg()
    {
        return AjaxResult.success();
    }


}
