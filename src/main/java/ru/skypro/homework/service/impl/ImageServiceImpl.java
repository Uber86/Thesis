package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.model.Image;
import ru.skypro.homework.repository.ImageRepository;
import ru.skypro.homework.service.ImageService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ImageServiceImpl implements ImageService {

    @Value("${app.images.dir}")
    private String imagesDirect;

    private final ImageRepository repository;


    @Override
    public Image saveImage(MultipartFile file) throws IOException {
        Image image = new Image();
        String uuid = UUID.randomUUID().toString();
        String fileName = uuid + "_" + file.getOriginalFilename();
        Path dirPath = Paths.get(imagesDirect);
        Files.createDirectories(dirPath);
        Path filePath = dirPath.resolve(fileName);
        Files.write(filePath, file.getBytes());

        image.setId(uuid);
        image.setFileName(fileName);
        image.setFileSize(file.getSize());
        image.setMediaType(file.getContentType());
        return repository.save(image);
    }

    @Override
    public byte[] loadImage(String fileName) throws IOException {
        Path filePath = Paths.get(imagesDirect, fileName);
        return Files.readAllBytes(filePath);
    }

    @Override
    public Image findById(String id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Image not found"));
    }
}
