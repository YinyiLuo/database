package com.dbdev.music.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.domain.Sort;

@Getter
@Setter
@ToString
public class ListenWithExtraInfo extends Listen{
    private String trackName;

    public ListenWithExtraInfo(Listen ls, String trackName) {
        super(ls.getUserId(), ls.getTrackId(), ls.getLatestDateTimePlaybackBegan());
        this.setId(ls.getId());
        this.setVersion(ls.getVersion());
        this.setCreateTime(ls.getCreateTime());
        this.setUpdateTime(ls.getUpdateTime());
        this.trackName = trackName;
    }

    static public Sort sort = Sort.by(Sort.Direction.ASC, "createTime");
}
