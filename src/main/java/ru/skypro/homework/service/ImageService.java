package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
    Integer saveImage(MultipartFile file);

    byte [] getImage(Integer id);

}
