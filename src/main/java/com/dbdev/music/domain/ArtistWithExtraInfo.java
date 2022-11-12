package com.dbdev.music.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ArtistWithExtraInfo extends Artist{
    private Long numAlbums;

    private Long numTracks;

    public ArtistWithExtraInfo(Artist ar, Long numAlbums, Long numTracks) {
        super(ar.getName(), ar.getUserId());
        this.setId(ar.getId());
        this.setCreateTime(ar.getCreateTime());
        this.setUpdateTime(ar.getUpdateTime());
        this.setVersion(ar.getVersion());
        this.numAlbums = numAlbums;
        this.numTracks = numTracks;
    }
}
