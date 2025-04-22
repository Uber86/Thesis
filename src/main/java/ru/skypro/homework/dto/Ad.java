package ru.skypro.homework.dto;

import lombok.Data;

/**
 * Класс, представляющий объявление (Ad).
 * <p>
 * Содержит информацию об авторе, изображении, уникальном идентификаторе,
 * цене и заголовке объявления.
 * </p>
 */
@Data
public class Ad {

    /**
     * Идентификатор автора объявления.
     */
    private int author;

    /**
     * URL или путь к изображению объявления.
     */
    private String image;

    /**
     * Уникальный первичный ключ объявления.
     */
    private int pk;

    /**
     * Цена товара или услуги в объявлении.
     */
    private int price;

    /**
     * Заголовок объявления.
     */
    private String title;
}