package ru.skypro.homework.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String username;
    private String password;
    private boolean enabled;

    @OneToMany
    private List<Ads> adsList;

    @OneToMany
    private List<Comment> commentList;


}
