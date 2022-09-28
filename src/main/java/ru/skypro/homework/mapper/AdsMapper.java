package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.CreateAdsDto;
import ru.skypro.homework.dto.FullAdsDto;
import ru.skypro.homework.model.Ads;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AdsMapper {

    Ads createAdsDtoToAdsEntity(CreateAdsDto createAdsDto);

    @Mapping(target = "image", ignore = true)
    Ads createAdsToAdsToEntity(CreateAdsDto createAdsDto);

    @Mapping(source = "id", target = "pk")
    @Mapping(source = "user.id", target = "author")
    AdsDto adsEntityToAdsTo(Ads ads);

    @Mapping(source = "user.firstName", target = "authorFirstName")
    @Mapping(source = "user.lastName", target = "authorLastName")
    @Mapping(source = "user.email", target = "email")
    @Mapping(source = "user.phone", target = "phone")
    @Mapping(source = "id", target = "pk")
    FullAdsDto adsEntityToFullAdsDto(Ads ads);

    List<AdsDto> adsEntitiesToAdsDto(List<Ads> advertList);

}
