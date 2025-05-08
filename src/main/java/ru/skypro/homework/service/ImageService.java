package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.model.Image;

import java.io.IOException;

/**
 * Интерфейс для работы с изображениями.
 */
public interface ImageService {

    /**
     * Сохраняет изображение на сервере.
     *
     * @param file файл изображения, который нужно сохранить.
     * @return объект Image, представляющий сохраненное изображение.
     * @throws IOException если произошла ошибка при сохранении файла.
     */
    Image saveImage(MultipartFile file) throws IOException;

    /**
     * Загружает изображение по его имени файла.
     *
     * @param fileName имя файла изображения, которое нужно загрузить.
     * @return массив байтов, представляющий содержимое изображения.
     * @throws IOException если произошла ошибка при загрузке файла.
     */
    byte[] loadImage(String fileName) throws IOException;

    /**
     * Находит изображение по его идентификатору.
     *
     * @param id идентификатор изображения, которое нужно найти.
     * @return объект Image, представляющий найденное изображение;
     *         или null, если изображение с указанным идентификатором не найдено.
     */
    Image findById(String id);
}