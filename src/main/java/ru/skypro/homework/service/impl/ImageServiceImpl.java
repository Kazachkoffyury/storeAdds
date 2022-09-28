package ru.skypro.homework.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.model.Image;
import ru.skypro.homework.repository.ImageRepository;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@Service
public class ImageServiceImpl {
    private final ImageRepository imageRepository;

    public ImageServiceImpl(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public byte[] downloadImage(String id) {
        Optional<Image> imageOptional = imageRepository.findById(id);
        byte[] images = null;
        if (imageOptional.isPresent()) {
            images = imageOptional.get().getData();
        }
        return images;
    }


    public String saveImage(MultipartFile file) {
        Image image = new Image();
        try {
            byte[] bytes = file.getBytes();
            image.setData(bytes);
        } catch (
                IOException e) {
            throw new RuntimeException(e);
        }
        image.setId(Long.valueOf(UUID.randomUUID().toString()));
        image.setFileSize((int) file.getSize());
        image.setMediaType(file.getContentType());
        Image savedImage = imageRepository.save(image);
        return String.valueOf(savedImage.getId());
    }
}
