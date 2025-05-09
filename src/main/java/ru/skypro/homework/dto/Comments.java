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
    private List<Comment> results;

    public Comments() {
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<Comment> getComments() {
        return results;
    }

    public void setComments(List<Comment> comments) {
        this.results = comments;
    }
}
