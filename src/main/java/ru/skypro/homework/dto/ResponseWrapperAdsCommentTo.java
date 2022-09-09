package ru.skypro.homework.dto;

import lombok.Data;

import java.util.List;

@Data
public class ResponseWrapperAdsCommentTo {
  private int count;
  private List<AdsCommentTo> results;
}

