package ru.skypro.homework.dto;

import lombok.Data;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;


@Data
public class Register {

    @Size(min = 4, max = 32, message = "Минимальное количество символов 4," +
            " максимальное количество символов 32")
    private String username;
    @Size(min = 8, max = 16, message = "Минимальное количество символов 8," +
            " максимальное количество символов 16")
    private String password;
    @Size(min = 2, max = 16, message = "Минимальное количество символов 2," +
            " максимальное количество символов 16")
    private String firstName;
    @Size(min = 2, max = 16, message = "Минимальное количество символов 2," +
            " максимальное количество символов 16")
    private String lastName;
    @Pattern(regexp = "\\+7\\s?\\(?\\d{3}\\)?\\s?\\d{3}-?\\d{2}-?\\d{2}",
    message = "Неверный формат номера телефона")
    private String phone;

    private List<Role> role;
}
