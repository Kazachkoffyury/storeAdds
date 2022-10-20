package ru.skypro.homework.exсeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.IOException;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class FailedToSaveImageException extends RuntimeException{

    public FailedToSaveImageException(IOException e) {
        super("Failed to save image");
    }

}
