package ru.skypro.homework.dto;


import lombok.Data;

import javax.validation.constraints.PastOrPresent;
import java.time.OffsetDateTime;

@Data
public class AdsCommentDto {
    private int author;
    @PastOrPresent
    private OffsetDateTime createdAt;
    private int pk;
    private String text;
}
