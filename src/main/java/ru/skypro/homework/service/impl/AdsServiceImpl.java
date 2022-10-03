package ru.skypro.homework.service.impl;

import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.*;

import ru.skypro.homework.exeption.NotFoundExeption;
import ru.skypro.homework.mapper.AdsMapper;
import ru.skypro.homework.model.Ads;
import ru.skypro.homework.repository.AdsRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.service.AdsService;

import javax.transaction.Transactional;
import java.time.OffsetDateTime;
import java.util.List;

@Service
public class AdsServiceImpl implements AdsService {

    private final AdsRepository adsRepository;
    private final AdsMapper adsMapper;
    private final CommentRepository commentRepository;

    public AdsServiceImpl(AdsRepository adsRepository, AdsMapper adsMapper, CommentRepository commentRepository) {
        this.adsRepository = adsRepository;
        this.adsMapper = adsMapper;
        this.commentRepository = commentRepository;
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

    @Override
    public AdsDto createAds(CreateAdsDto createAdsDto) {
        Ads createdAds = adsMapper.createAdsToAdsToEntity(createAdsDto);
        adsRepository.save(createdAds);
        return adsMapper.adsEntityToAdsTo(createdAds);
    }



    @Override
    public void removeAds(Integer id) {
        adsRepository.deleteById(id);
    }

    @Override
    public AdsDto updateAds(Integer id, AdsDto adsDto) {
      Ads ads = adsRepository.findById(id).orElseThrow(NotFoundExeption:: new);
      ads.setPrice(adsDto.getPrice());
      ads.setDescription(adsDto.getDescription());
      ads.setTitle(adsDto.getTitle());
      adsRepository.save(ads);

      return adsDto;
    }

    @Override
    public Ads getAds(int id) {
      Ads ads = adsRepository.findById(id).orElseThrow(NotFoundExeption::new);
        return  ads;
    }


    @Override
    public ResponseWrapperAdsDto findAds(String search) {

        List<AdsDto> adsSearch = adsMapper.adsEntitiesToAdsDto(adsRepository.findAllByTitleContainsIgnoreCase(search));
        ResponseWrapperAdsDto responseWrapperAdsDto =  new ResponseWrapperAdsDto();
        responseWrapperAdsDto.setCount(adsSearch.size());
        responseWrapperAdsDto.setResults(adsSearch);
        return responseWrapperAdsDto;
    }


    @Override
    public ResponseWrapperAdsDto getAdsMe(String name) {
        return null;
    }
}
