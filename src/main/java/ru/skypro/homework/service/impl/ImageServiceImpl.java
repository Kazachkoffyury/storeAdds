package ru.skypro.homework.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.exсeption.FailedToSaveImageException;
import ru.skypro.homework.model.Image;
import ru.skypro.homework.repository.ImageRepository;
import ru.skypro.homework.service.ImageService;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@Service
public class ImageServiceImpl implements ImageService {
    private final ImageRepository imageRepository;

    public ImageServiceImpl(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public byte[] getImage(Integer id) {
        Optional<Image> imageOptional = imageRepository.findById(id);
        byte[] images = null;
        if (imageOptional.isPresent()) {
            images = imageOptional.get().getData();
        }
        return images;
    }


    public Integer saveImage(MultipartFile file) {
        Image image = new Image();
        try {
            byte[] bytes = file.getBytes();
            image.setData(bytes);
        } catch (
                IOException e) {
            throw new FailedToSaveImageException(e);
        }
        Image saveImage = imageRepository.saveAndFlush(image);
        return saveImage.getId();
    }
}
