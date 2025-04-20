package ru.skypro.homework.dto;

import lombok.Data;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class UpdateUser {

    @Size(min = 3, max = 10, message = "Минимальное количество символов 3," +
            " максимальное количество символов 10")
    private String firstName;
    @Size(min = 3, max = 10, message = "Минимальное количество символов 3," +
            " максимальное количество символов 10")
    private String lastName;
    @Pattern(regexp = "\\+7\\s?\\(?\\d{3}\\)?\\s?\\d{3}-?\\d{2}-?\\d{2}",
            message = "Неверный формат номера телефона")
    private String phone;

}
