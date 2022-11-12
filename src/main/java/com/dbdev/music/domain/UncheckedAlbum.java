package com.dbdev.music.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@Setter
@ToString

public class UncheckedAlbum extends Album{
    private List<Track> containedTracks;

    public UncheckedAlbum(Album al, List<Track> containedTracks) {
        super(al.getName(), al.getDescription(), al.getChecked());
        this.setId(al.getId());
        this.setVersion(al.getVersion());
        this.setCreateTime(al.getCreateTime());
        this.setUpdateTime(al.getUpdateTime());
        this.containedTracks = containedTracks;
    }
}
