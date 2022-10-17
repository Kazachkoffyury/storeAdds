package ru.skypro.homework.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import ru.skypro.homework.dto.AdsCommentDto;

import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.CreateAdsDto;
import ru.skypro.homework.dto.ResponseWrapperAdsDto;
import ru.skypro.homework.model.Ads;
import ru.skypro.homework.model.Comment;

import java.util.List;

public interface AdsService {
    ResponseWrapperAdsDto getAllAds();
    AdsDto createAds(CreateAdsDto createAdsDto, Authentication authentication);
    void removeAds(Integer id, String name, UserDetails userDetails);
    AdsDto updateAds(Integer id, AdsDto adsDto,String name,UserDetails userDetails);
    Ads getAds(int search);
    ResponseWrapperAdsDto findAds( String search);
    ResponseWrapperAdsDto getAdsMe(String name);

}
