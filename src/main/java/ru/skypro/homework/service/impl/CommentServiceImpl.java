package ru.skypro.homework.service.impl;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.AdsCommentDto;
import ru.skypro.homework.dto.ResponseWrapperAdsCommentDto;
import ru.skypro.homework.exception.NoAccessException;
import ru.skypro.homework.exception.NotFoundException;
import ru.skypro.homework.exception.UserNotFoundException;
import ru.skypro.homework.mapper.CommentMapper;
import ru.skypro.homework.model.Ads;
import ru.skypro.homework.model.Comment;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.AdsRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.CommentService;

import java.time.OffsetDateTime;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentMapper commentMapper;
    private final CommentRepository commentRepository;
    private final AdsRepository adsRepository;
    private final UserRepository userRepository;

    public CommentServiceImpl(CommentMapper commentMapper, CommentRepository commentRepository, AdsRepository adsRepository, UserRepository userRepository) {
        this.commentMapper = commentMapper;
        this.commentRepository = commentRepository;
        this.adsRepository = adsRepository;
        this.userRepository = userRepository;
    }

    @Override
    public AdsCommentDto createComment(Integer adsId, AdsCommentDto adsCommentDto, Authentication authentication) {
        Comment createdComment = commentMapper.adsCommentDtoToCommentEntity(adsCommentDto);
        User user = userRepository.findUserByUserName(authentication.getName()).orElseThrow(UserNotFoundException::new);
        createdComment.setUser(user);
        Ads advert = adsRepository.findById(adsId).orElseThrow(NotFoundException::new);
        createdComment.setAds(advert);
        createdComment.setCreatedAt(OffsetDateTime.now());
        commentRepository.save(createdComment);
        return adsCommentDto;
    }

    @Override
    public AdsCommentDto getAdsComment(Integer id) {
        Comment comment = commentRepository.findById(id).orElseThrow(NotFoundException::new);
        AdsCommentDto adsCommentDto = commentMapper.commentEntityToAdsCommentDto(comment);
        return adsCommentDto;
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
    public void deleteAdsComment(Integer ad_pk, Integer id, String username, UserDetails userDetails) {
        Comment comment = commentRepository.findById(id).orElseThrow(NotFoundException::new);
        User user = userRepository.getById(comment.getUser().getId());
        if (userDetails.getAuthorities().toString().contains("ROLE_ADMIN")
                || username.equals(user.getUserName())) {
            commentRepository.delete(comment);
        } else {
            throw new NoAccessException();
        }

    }

    @Override
    public AdsCommentDto updateAdsComment(Integer ads_id, Integer id, AdsCommentDto adsCommentDto, String userName, UserDetails userDetails) {
        Comment comment = commentRepository.findCommentById(id).orElseThrow(NotFoundException::new);
        User user = userRepository.getById(comment.getUser().getId());
        if (userDetails.getAuthorities().toString().contains("ROLE_ADMIN")
                || userName.equals(user.getUserName())) {
            comment.setCreatedAt(adsCommentDto.getCreatedAt());
            comment.setText(adsCommentDto.getText());
            commentRepository.save(comment);
            return adsCommentDto;
        } else {
            throw new NoAccessException();
        }
    }
}
