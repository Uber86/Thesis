package ru.skypro.homework.dto;

import lombok.Data;

import java.util.Map;

@Data
public class Ads {

    private Integer count;
    private Map<Integer, Ad> ads;
}
