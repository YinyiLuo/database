package com.dbdev.music.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long trackId;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
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
