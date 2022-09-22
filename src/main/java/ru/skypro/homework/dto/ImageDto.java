package ru.skypro.homework.dto;

import lombok.Data;

@Data

public class ImageDto {

    private String pk;

    private Long idAds;

    private Integer fileSize;

    private String mediaType;
}
