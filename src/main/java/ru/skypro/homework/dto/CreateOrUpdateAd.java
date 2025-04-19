package ru.skypro.homework.dto;

import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class CreateOrUpdateAd {

    @Size(min = 4, max = 32, message = "Минимальное количество символов 4," +
            " максимальное количество символов 32")
    private String title;

    @Size(max = 1_000_0000, message = "Максимальное цена 10000000")
    private int price;

    @Size(min = 8, max = 64, message = "Минимальное количество символов 8," +
            " максимальное количество символов 64")
    private String description;

}
