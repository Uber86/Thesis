package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class NewPassword {
    private String username;
    private String currentPassword;
    private String newPassword;
}
