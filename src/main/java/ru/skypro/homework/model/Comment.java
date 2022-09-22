package ru.skypro.homework.model;

import lombok.Data;
import lombok.Setter;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Data
@Entity
@Setter
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private OffsetDateTime createdAt;
    private String text;

    @ManyToOne
    private User user;

    @ManyToOne
    private Ads ads;
}
