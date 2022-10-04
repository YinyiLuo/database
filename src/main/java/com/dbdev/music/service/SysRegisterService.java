package com.dbdev.music.service;

import com.dbdev.music.config.MailSenderHelper;
import com.dbdev.music.core.redis.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class SysRegisterService {
    @Autowired
    private MailSenderHelper mailSenderHelper;

    @Autowired
    private RedisCache redisCache;

    public String getCode(String email, String mark) {
        Random random = new Random();
        String code = String.valueOf(random.nextInt(1, 10) * 100000 + random.nextInt(100000));
        mailSenderHelper.send(email, code);
        redisCache.setCacheObject(email + ":" + mark, code);
        return code;
    }
}
