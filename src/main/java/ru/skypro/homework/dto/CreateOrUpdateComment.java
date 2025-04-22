package ru.skypro.homework.dto;

import lombok.Data;

import javax.validation.constraints.Size;

/**
 * Класс, представляющий данные для создания или обновления комментария (CreateOrUpdateComment).
 * <p>
 * Содержит текст комментария, который должен соответствовать определенным требованиям.
 * </p>
 */
@Data
public class CreateOrUpdateComment {

    /**
     * Текст комментария.
     * <p>
     * Должен содержать от 8 до 32 символов.
     * </p>
     */
    @Size(min = 8, max = 32, message = "Минимальное количество символов 8, максимальное количество символов 32")
    private String text;
}
