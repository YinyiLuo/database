package com.dbdev.music.config;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.net.NetUtil;
import cn.hutool.core.util.IdUtil;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.util.TypeInformation;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.Serializable;


public class MyIdGeneratorConfig implements IdentifierGenerator {
    private final long WORKER_ID = 1;
    private final long DATACENTER_ID = 1;
    private final Snowflake snowflake = IdUtil.createSnowflake(WORKER_ID, DATACENTER_ID);

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        return snowflake.nextId();
    }
}
