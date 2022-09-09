package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class NewPasswordTo {
    private String currentPassword;
    private String newPassword;
}


