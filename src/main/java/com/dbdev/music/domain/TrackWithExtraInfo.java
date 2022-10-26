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

    public TrackWithExtraInfo(Track tc, String artist_name, String album_name) {
        super(tc.getName(), tc.getFile(), tc.isChecked(), tc.getTimeLength());
        this.setId(tc.getId());
        this.setCreateTime(tc.getCreateTime());
        this.setUpdateTime(tc.getUpdateTime());
        this.setVersion(tc.getVersion());
        this.artistName = artist_name;
        this.albumName = album_name;
    }
}
