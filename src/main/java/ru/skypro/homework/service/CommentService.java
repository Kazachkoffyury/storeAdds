package ru.skypro.homework.service;

import ru.skypro.homework.dto.AdsCommentDto;
import ru.skypro.homework.dto.ResponseWrapperAdsCommentDto;

public interface CommentService {
    AdsCommentDto createComment(Integer adsId, AdsCommentDto adsCommentDto);

    AdsCommentDto getAdsComment(Integer adsId, Integer id);

    ResponseWrapperAdsCommentDto getAdsAllComments(Integer adsId);

    void deleteAdsComment(Integer adsId, Integer id, String username);

    AdsCommentDto updateAdsComment(Integer adsId, Integer id, AdsCommentDto adsCommentDto, String username);
}
