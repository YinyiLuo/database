package com.dbdev.music.controller;

import com.dbdev.music.config.MailSenderHelper;
import com.dbdev.music.core.AjaxResult;
import com.dbdev.music.domain.Album;
import com.dbdev.music.domain.UncheckedAlbum;
import com.dbdev.music.repository.AlbumRepository;
import com.dbdev.music.repository.ArtistRepository;
import com.dbdev.music.repository.SysUserRepository;
import com.dbdev.music.repository.TrackRepository;
import com.dbdev.music.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Controller
@ResponseBody
public class CheckController {
    @Autowired
    private MailSenderHelper mailSenderHelper;

    @Autowired
    private ArtistRepository artistRepository;

    @Autowired
    private SysUserRepository sysUserRepository;

    @Autowired
    AlbumRepository albumRepository;

    @Autowired
    TokenService tokenService;

    @Autowired
    TrackRepository trackRepository;

    @PostMapping("/check/{id}/{valid}")
    @Transactional
    public AjaxResult Check(@PathVariable("id") Long id, @PathVariable("valid") int valid)
    {
        Optional<Album> byId = albumRepository.findById(id);
        Album album = null;
        if(byId.isPresent())
        {
            album=byId.get();
        }

        StringBuilder msg = new StringBuilder("亲爱的用户：\n  您上传的专辑《" +
                albumRepository.findById(id).get().getName() + "》已由管理员审核。\n  审核结果为：");

        if(valid == 1)
        {
            try {
                album.setChecked(true);
                albumRepository.save(album);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            albumRepository.deleteById(id);
            msg.append("不");
        }

        msg.append("通过！\n  ");
        if (valid == 1) {
            msg.append("谢谢您的支持！");
        }
        else {
            msg.append("审核不通过的原因为：");
        }

        mailSenderHelper.sendMessage(sysUserRepository.findById(artistRepository.findByAlbumId(id).getUserId()).get().getEmail(),
                msg.toString(), "审核结果回执信");
        System.out.println("before");
        return AjaxResult.success();
    }


    @GetMapping("/check/getAllUnchecked")
    public AjaxResult getAllUnchecked()
    {
        List<Album> tmpAlbums = albumRepository.findByCheckedIsFalse();
        List<UncheckedAlbum> uncheckedAlbums = new ArrayList<>();
        for (Album al : tmpAlbums) {
            uncheckedAlbums.add(new UncheckedAlbum(al,
                    trackRepository.findContainedTracksByAlbumId(al.getId(), PageRequest.of(0, 20)).getContent()));
        }
        return AjaxResult.success(uncheckedAlbums);
    }
}
