package ru.skypro.homework.dto;

import lombok.Data;

import java.util.List;


/**
 * Класс, представляющий коллекцию объявлений (Ads).
 * <p>
 * Содержит общее количество объявлений и список самих объявлений.
 * </p>
 */
@Data
public class Ads {

    /**
     * Общее количество объявлений.
     */
    private int count;

    /**
     * Список объявлений.
     */
    private List<Ad> results;
}
