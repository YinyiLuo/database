package com.dbdev.music.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Entity;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;


@Entity
@ToString
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Track extends BaseEntity {
    private String name;

    private UUID file;

    private String timeLength;

    private String suffix;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Track track = (Track) o;
        return Objects.equals(name, track.name) && Objects.equals(file, track.file) && Objects.equals(timeLength, track.timeLength) && Objects.equals(suffix, track.suffix);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, file, timeLength, suffix);
    }
}
