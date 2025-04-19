package ru.skypro.homework.dto;

import lombok.Data;

import javax.validation.constraints.Size;


@Data
public class Login {
    @Size(min = 8, max = 16, message = "Минимальное количество символов 8," +
            " максимальное количество символов 16")
    private String password;
    @Size(min = 4, max = 32, message = "Минимальное количество символов 2," +
            " максимальное количество символов 32")
    private String username;

}
