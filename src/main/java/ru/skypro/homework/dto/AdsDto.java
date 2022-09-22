package ru.skypro.homework.dto;

import lombok.Data;

import javax.validation.constraints.Positive;

@Data
public class AdsDto {
    private int author;
    private int pk;
    @Positive
    private int price;
    private String title;
    private String description;


}
