package com.dbdev.music.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TrackWithExtraInfo extends Track{
    private String artistName;

    private String albumName;

    public TrackWithExtraInfo(Track tc, String artistName, String albumName) {
        super(tc.getName(), tc.getFile(), tc.isChecked(), tc.getTimeLength());
        this.setId(tc.getId());
        this.setCreateTime(tc.getCreateTime());
        this.setUpdateTime(tc.getUpdateTime());
        this.setVersion(tc.getVersion());
        this.artistName = artistName;
        this.albumName = albumName;
    }
}
