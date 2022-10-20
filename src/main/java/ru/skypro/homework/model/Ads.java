package ru.skypro.homework.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Ads {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer price;
    private String title;

    @Lob
    private String image;
    private String description;

    @ManyToOne
    private User user;

    @OneToMany
    private List<Comment> commentList;


}
