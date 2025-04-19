package ru.skypro.homework.dto;

import lombok.Data;

import java.util.Map;

@Data
public class Comments {
    private Integer count;
    private Map<Integer, Comment> comments;

}
