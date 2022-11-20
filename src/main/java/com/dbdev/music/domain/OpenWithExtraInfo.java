package com.dbdev.music.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.domain.Sort;

@Getter
@Setter
@ToString
public class OpenWithExtraInfo extends Open{
    private String albumName;

    public OpenWithExtraInfo(Open op, String albumName) {
        super(op.getUserId(), op.getAlbumId(), op.getLatestDateTimePlaybackBegan());
        this.setId(op.getId());
        this.setVersion(op.getVersion());
        this.setCreateTime(op.getCreateTime());
        this.setUpdateTime(op.getUpdateTime());
        this.albumName = albumName;
    }

    static public Sort sort = Sort.by(Sort.Direction.ASC, "createTime");
}
