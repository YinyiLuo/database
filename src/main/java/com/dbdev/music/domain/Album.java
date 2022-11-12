package com.dbdev.music.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Entity;
import java.time.LocalDateTime;
import java.util.Objects;


@Entity
@ToString
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Album extends BaseEntity {
    private String name;

    private String description;

    private Boolean checked;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Album album = (Album) o;
        return Objects.equals(name, album.name) && Objects.equals(description, album.description) && Objects.equals(checked, album.checked);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, description, checked);
    }
}
