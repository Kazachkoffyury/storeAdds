package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.skypro.homework.dto.ImageDto;
import ru.skypro.homework.model.Ads;
import ru.skypro.homework.model.Image;

@Mapper(componentModel = "spring")
public interface ImageMapper {
    @Mapping(source = "ads.id", target = "idAds")
    @Mapping(source = "image.id", target = "pk")
    @Mapping(source = "image.fileSize", target = "fileSize")
    @Mapping(source = "image.mediaType", target = "mediaType")
    ImageDto imageToImageTo(Image image, Ads ads);
}
