package ru.skypro.homework.service.impl;

import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.AdsCommentDto;
import ru.skypro.homework.dto.ResponseWrapperAdsCommentDto;
import ru.skypro.homework.exeption.NotFoundExeption;
import ru.skypro.homework.mapper.CommentMapper;
import ru.skypro.homework.model.Ads;
import ru.skypro.homework.model.Comment;
import ru.skypro.homework.repository.AdsRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.service.CommentService;

import java.time.OffsetDateTime;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentMapper commentMapper;
    private final CommentRepository commentRepository;
    private final AdsRepository adsRepository;

    public CommentServiceImpl(CommentMapper commentMapper, CommentRepository commentRepository, AdsRepository adsRepository) {
        this.commentMapper = commentMapper;
        this.commentRepository = commentRepository;
        this.adsRepository = adsRepository;
    }

    @Override
    public AdsCommentDto createComment(Integer adsId, AdsCommentDto adsCommentDto) {
        Comment createdComment = commentMapper.adsCommentDtoToCommentEntity(adsCommentDto);
        Ads ads = adsRepository.findById(adsId).orElseThrow(NotFoundExeption::new);
        createdComment.setAds(ads);
        createdComment.setCreatedAt(OffsetDateTime.now());
        commentRepository.save(createdComment);
        return adsCommentDto;
    }

    @Override
    public AdsCommentDto getAdsComment(Integer adsId, Integer id) {
        return null;
    }

    @Override
    public ResponseWrapperAdsCommentDto getAdsAllComments(Integer adsId) {
        List<AdsCommentDto> adsCommentList = commentMapper.commentEntitiesToAdsCommentDto(commentRepository.findByAdsId(adsId));
        ResponseWrapperAdsCommentDto responseWrapperAdsCommentTo = new ResponseWrapperAdsCommentDto();
        responseWrapperAdsCommentTo.setCount(adsCommentList.size());
        responseWrapperAdsCommentTo.setResults(adsCommentList);
        return responseWrapperAdsCommentTo;
    }

    @Override
    public void deleteAdsComment(Integer adsId, Integer id, String username) {

    }

    @Override
    public AdsCommentDto updateAdsComment(Integer adsId, Integer id, AdsCommentDto adsCommentDto, String username) {
        return null;
    }
}
