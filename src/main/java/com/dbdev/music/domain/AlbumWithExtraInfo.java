package com.dbdev.music.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AlbumWithExtraInfo extends Album{
    private String artistName;

    private Long numTracks;

    public AlbumWithExtraInfo(Album al, String artistName, Long numTracks) {
        super(al.getName(), al.getDescription());
        this.setId(al.getId());
        this.setCreateTime(al.getCreateTime());
        this.setUpdateTime(al.getUpdateTime());
        this.setVersion(al.getVersion());
        this.artistName = artistName;
        this.numTracks = numTracks;
    }
}
