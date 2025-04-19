package ru.skypro.homework.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class NewPassword {

    @Size(min = 8, max = 16, message = "Минимальное количество символов 8," +
            " максимальное количество символов 16")
    private String currentPassword;
    @Size(min = 8, max = 16, message = "Минимальное количество символов 8," +
            " максимальное количество символов 16")
    private String newPassword;
}
