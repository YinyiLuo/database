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

    private boolean checked;

    private String timeLength;

    @CreatedDate
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime latest_time_played;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Track track = (Track) o;
        return checked == track.checked && Objects.equals(name, track.name) && Objects.equals(file, track.file) && Objects.equals(timeLength, track.timeLength);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, file, checked, timeLength);
    }
}
