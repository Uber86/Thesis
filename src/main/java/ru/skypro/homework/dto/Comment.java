package ru.skypro.homework.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

@Data
public class Comment {

    private int author;
    private String authorImage;
    private String authorFirstName;
    @Pattern(regexp = "(\\d{2}\\.\\d{2}\\.\\d{4}\\s\\d{2}:\\d{2})(\\s+)(.+)")
    private LocalDateTime createAt;
    private int pk;
    private String text;
}
