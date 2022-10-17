package ru.skypro.homework.service.impl;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.*;



import ru.skypro.homework.exсeption.NoAccessException;
import ru.skypro.homework.exсeption.NotFoundException;
import ru.skypro.homework.exсeption.UserNotFoundException;
import ru.skypro.homework.mapper.AdsMapper;
import ru.skypro.homework.model.Ads;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.AdsRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AdsService;

import java.util.List;

@Service
public class AdsServiceImpl implements AdsService {

    private final AdsRepository adsRepository;
    private final AdsMapper adsMapper;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    public AdsServiceImpl(AdsRepository adsRepository, AdsMapper adsMapper, CommentRepository commentRepository, UserRepository userRepository) {
        this.adsRepository = adsRepository;
        this.adsMapper = adsMapper;
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
    }

    /**
     * получение всех обьявлений из БД
     *
     * @return list as ResponseWrapperAdsTo (DTO)
     */
    @Override
    public ResponseWrapperAdsDto getAllAds() {
        List<AdsDto> adsList = adsMapper.adsEntitiesToAdsDto(adsRepository.findAll());
        ResponseWrapperAdsDto responseWrapperAds = new ResponseWrapperAdsDto();
        responseWrapperAds.setCount(adsList.size());
        responseWrapperAds.setResults(adsList);
        return responseWrapperAds;
    }

    /** Создание обьявления
     *
     * @param createAdsDto
     * @param authentication
     * @return
     */
    @Override
    public AdsDto createAds(CreateAdsDto createAdsDto, Authentication authentication) {
        Ads createdAds = adsMapper.createAdsToAdsToEntity(createAdsDto);
        createdAds.setUser(userRepository.findUserByUserName(authentication.getName()).orElseThrow(UserNotFoundException::new));
        adsRepository.save(createdAds);
        return adsMapper.adsEntityToAdsTo(createdAds);
    }


    /** Удаление объявления
     *
     * @param id
     * @param userName
     * @param userDetails
     */
    @Override
    public void removeAds(Integer id, String userName,UserDetails userDetails) {
        Ads ads = adsRepository.findById(id).orElseThrow(NotFoundException::new);

        if (userDetails.getAuthorities().toString().contains("ROLE_ADMIN")
                || userName.equals(ads.getUser().getUserName())) {
            adsRepository.delete(ads);
        } else {
            throw new NoAccessException();
        }
    }

    /** редактирование обьявления
     *
     * @param id
     * @param adsDto
     * @param username
     * @param userDetails
     * @return
     */
    @Override
    public AdsDto updateAds(Integer id, AdsDto adsDto, String username, UserDetails userDetails) {
        Ads ads = adsRepository.findById(id).orElseThrow(NotFoundException::new);

        if (userDetails.getAuthorities().toString().contains("ROLE_ADMIN")
                || username.equals(ads.getUser().getUserName())) {
            ads.setPrice(adsDto.getPrice());
            ads.setTitle(adsDto.getTitle());
            ads.setDescription(adsDto.getDescription());
            adsRepository.save(ads);
            return adsMapper.adsEntityToAdsTo(ads);
        } else {
            throw new NoAccessException();
        }
    }

    /** получение объявления по id
     *
     * @param id
     * @return
     */
    @Override
    public Ads getAds(int id) {
        Ads ads = adsRepository.findById(id).orElseThrow(NotFoundException::new);
        return  ads;
    }


    /** поиск по объявлениям
     *
     * @param search
     * @return
     */
    @Override
    public ResponseWrapperAdsDto findAds(String search) {

        List<AdsDto> adsSearch = adsMapper.adsEntitiesToAdsDto(adsRepository.findAllByTitleContainsIgnoreCase(search));
        ResponseWrapperAdsDto responseWrapperAdsDto =  new ResponseWrapperAdsDto();
        responseWrapperAdsDto.setCount(adsSearch.size());
        responseWrapperAdsDto.setResults(adsSearch);
        return responseWrapperAdsDto;
    }

    /** получение своих обьявления юзера
     *
     * @param userName
     * @return
     */
    @Override
    public ResponseWrapperAdsDto getAdsMe(String userName) {
        User user = userRepository.findUserByUserName(userName).orElseThrow(UserNotFoundException::new);
        List<AdsDto> adsDtoList = adsMapper.adsEntitiesToAdsDto(adsRepository.findAllByUserId(user.getId()));
        ResponseWrapperAdsDto responseWrapperAdsDto = new ResponseWrapperAdsDto();
        responseWrapperAdsDto.setCount(adsDtoList.size());
        responseWrapperAdsDto.setResults(adsDtoList);
        return responseWrapperAdsDto;
    }
}