package ru.skypro.homework.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Objects;

@Entity
@Getter
@Setter
public class Image {
    @Id
    @Column(name = "id", unique = true)
    private Long id;
    private Integer fileSize;
    private String mediaType;
    @Lob
    private byte[] data;

    @OneToOne
    private Image image;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Image image = (Image) o;
        return Objects.equals(id, image.id) && Objects.equals(fileSize, image.fileSize) && Objects.equals(mediaType, image.mediaType) && Arrays.equals(data, image.data);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, fileSize, mediaType);
        result = 31 * result + Arrays.hashCode(data);
        return result;
    }
}
