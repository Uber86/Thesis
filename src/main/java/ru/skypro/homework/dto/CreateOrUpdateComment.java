package ru.skypro.homework.dto;

import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class CreateOrUpdateComment {

    @Size(min = 8, max = 32, message = "Минимальное количество символов 8," +
            " максимальное количество символов 32")
    private String text;
}
