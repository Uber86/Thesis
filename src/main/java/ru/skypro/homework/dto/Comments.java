package ru.skypro.homework.dto;

import lombok.Data;

import java.util.List;

/**
 * Класс, представляющий коллекцию комментариев (Comments).
 * <p>
 * Содержит общее количество комментариев и список самих комментариев.
 * </p>
 */
@Data
public class Comments {

    /**
     * Общее количество комментариев.
     */
    private int count;

    /**
     * Список комментариев.
     */
    private List<Comment> comments;
}
