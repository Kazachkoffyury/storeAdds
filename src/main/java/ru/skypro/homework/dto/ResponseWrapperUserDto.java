package ru.skypro.homework.dto;

import lombok.Data;

import java.util.List;

@Data
public class ResponseWrapperUserDto {
    private int count;
    private List<UserDto> results;
}


