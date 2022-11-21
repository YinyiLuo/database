package com.dbdev.music.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CollectWithExtraInfo extends Collect{
    String albumName;

    public CollectWithExtraInfo(Collect cl, String albumName) {
        super(cl.getUserId(), cl.getAlbumId(), cl.getCollectedTime());
        this.setId(cl.getId());
        this.setVersion(cl.getVersion());
        this.setCreateTime(cl.getCreateTime());
        this.setUpdateTime(cl.getUpdateTime());
        this.albumName = albumName;
    }
}
