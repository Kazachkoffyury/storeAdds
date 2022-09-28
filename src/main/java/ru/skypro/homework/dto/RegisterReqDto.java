package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class RegisterReqDto {
    private String username;
    private String password;
    private Role role;
}
