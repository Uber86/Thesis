package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class Ad {

    private Integer author;
    private String image;
    private Integer pk;
    private int price;
    private String title;
}
