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
public class Artist extends BaseEntity {
    private String name;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long userId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Artist artist = (Artist) o;
        return Objects.equals(name, artist.name) && Objects.equals(userId, artist.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, userId);
    }
}
