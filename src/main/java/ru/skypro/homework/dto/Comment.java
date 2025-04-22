package ru.skypro.homework.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

/**
 * Класс, представляющий комментарий (Comment).
 * <p>
 * Содержит информацию об авторе комментария, времени создания,
 * уникальном идентификаторе и тексте комментария.
 * </p>
 */
@Data
public class Comment {

    /**
     * Идентификатор автора комментария.
     */
    private int author;

    /**
     * URL или путь к изображению автора комментария.
     */
    private String authorImage;

    /**
     * Имя автора комментария.
     */
    private String authorFirstName;

    /**
     * Время создания комментария в формате "dd.MM.yyyy HH:mm".
     * <p>
     * Должно соответствовать регулярному выражению:
     * <code>(\\d{2}\\.\\d{2}\\.\\d{4}\\s\\d{2}:\\d{2})(\\s+)(.+)</code>.
     * </p>
     */
    @Pattern(regexp = "(\\d{2}\\.\\d{2}\\.\\d{4}\\s\\d{2}:\\d{2})(\\s+)(.+)")
    private LocalDateTime createAt;

    /**
     * Уникальный первичный ключ комментария.
     */
    private int pk;

    /**
     * Текст комментария.
     */
    private String text;
}
