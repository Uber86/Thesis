package ru.skypro.homework.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class AdDTO {

    private Long pk;
    private Long author;
    private String image;
    private int price;
    private String title;
    private String description;
}
