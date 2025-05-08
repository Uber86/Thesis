package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.model.Image;

import java.io.IOException;

public interface ImageService {

    public Image saveImage(MultipartFile file) throws IOException;

    public byte[] loadImage(String fileName) throws IOException;

    public Image findById(String id);
}
