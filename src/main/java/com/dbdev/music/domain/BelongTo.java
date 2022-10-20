package com.dbdev.music.domain;

import lombok.*;
import lombok.experimental.SuperBuilder;
import javax.persistence.Entity;
import java.util.Objects;


@Entity
@ToString
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class BelongTo extends BaseEntity {
    private Long trackId;

    private Long albumId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        BelongTo belong_to = (BelongTo) o;
        return trackId.equals(belong_to.trackId) && albumId.equals(belong_to.albumId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), trackId, albumId);
    }
}
