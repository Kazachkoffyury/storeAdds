package ru.skypro.homework.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import ru.skypro.homework.dto.AdsCommentDto;
import ru.skypro.homework.dto.ResponseWrapperAdsCommentDto;

public interface CommentService {
    AdsCommentDto createComment(Integer adsId, AdsCommentDto adsCommentDto, Authentication authentication);

    AdsCommentDto getAdsComment(Integer id);

    ResponseWrapperAdsCommentDto getAdsAllComments(Integer adsId);

    void deleteAdsComment(Integer adsId,Integer id, String username, UserDetails userDetails);

    AdsCommentDto updateAdsComment(Integer ad_pk,Integer id, AdsCommentDto adsCommentDto,String username, UserDetails userDetails);
}
