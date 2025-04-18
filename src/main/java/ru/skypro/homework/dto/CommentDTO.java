package ru.skypro.homework.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentDTO {

    private Long pk;
    private Long author;
    private String authorImage;
    private String authorFirstName;
    private LocalDateTime createAt;
    private String text;

}
