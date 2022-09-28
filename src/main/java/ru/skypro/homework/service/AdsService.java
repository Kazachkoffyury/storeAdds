package ru.skypro.homework.service;

import ru.skypro.homework.dto.AdsCommentDto;

import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.CreateAdsDto;
import ru.skypro.homework.dto.ResponseWrapperAdsDto;
import ru.skypro.homework.model.Ads;
import ru.skypro.homework.model.Comment;

import java.util.List;

public interface AdsService {
    ResponseWrapperAdsDto getAllAds();
    AdsDto createAds(CreateAdsDto createAdsDto);
    void removeAds(Integer id);
    AdsDto updateAds(Integer id, AdsDto adsDto);
    Ads findAds(int search);
    ResponseWrapperAdsDto getAdsMe(String name);

}
