package ru.skypro.homework.dto;


import lombok.Data;

@Data
public class CreateAdsDto {
    private String description;
    private int pk;
    private int price;
    private String title;
}
