package ru.skypro.homework.dto;

import lombok.Data;

import java.util.List;

@Data
public class ResponseWrapperAdsTo {
    private int count;
    private List<AdsTo> results;

}
