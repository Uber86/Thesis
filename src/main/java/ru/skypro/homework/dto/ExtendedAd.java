package ru.skypro.homework.dto;

import lombok.Data;

/**
 * Класс, представляющий расширенное объявление (ExtendedAd).
 * <p>
 * Содержит информацию об авторе, описании, контактных данных и других характеристиках объявления.
 * </p>
 */
@Data
public class ExtendedAd {

    /**
     * Уникальный первичный ключ объявления.
     */
    private int pk;

    /**
     * Имя автора объявления.
     */
    private String authorFirstName;

    /**
     * Фамилия автора объявления.
     */
    private String authorLastName;

    /**
     * Описание объявления.
     */
    private String description;

    /**
     * Электронная почта автора объявления.
     */
    private String email;

    /**
     * URL или путь к изображению, связанному с объявлением.
     */
    private String image;

    /**
     * Номер телефона автора объявления.
     */
    private String phone;

    /**
     * Цена объявления.
     */
    private int price;

    /**
     * Заголовок объявления.
     */
    private String title;
}
