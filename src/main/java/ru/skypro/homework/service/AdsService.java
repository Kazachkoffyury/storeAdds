package ru.skypro.homework.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;

import ru.skypro.homework.model.Ads;
import ru.skypro.homework.model.Comment;

import java.util.List;

public interface AdsService {
    ResponseWrapperAdsDto getAllAds();
    AdsDto createAds(CreateAdsDto createAdsDto, Authentication authentication, MultipartFile file);
    void removeAds(Integer id, String name, UserDetails userDetails);
    AdsDto updateAds(Integer id, AdsDto adsDto,String name,UserDetails userDetails,MultipartFile file);
    Ads getAds(int search);
    ResponseWrapperAdsDto findAds( String search);
    ResponseWrapperAdsDto getAdsMe(String name);

}
