package ru.skypro.homework.dto;

import lombok.Data;

import javax.validation.constraints.Size;

/**
 * Класс, представляющий данные для изменения пароля (NewPassword).
 * <p>
 * Содержит информацию о текущем и новом пароле, которые должны соответствовать определенным требованиям.
 * </p>
 */
@Data
public class NewPassword {

    /**
     * Текущий пароль пользователя.
     * <p>
     * Должен содержать от 8 до 16 символов.
     * </p>
     */
    @Size(min = 8, max = 16, message = "Минимальное количество символов 8, максимальное количество символов 16")
    private String currentPassword;

    /**
     * Новый пароль пользователя.
     * <p>
     * Должен содержать от 8 до 16 символов.
     * </p>
     */
    @Size(min = 8, max = 16, message = "Минимальное количество символов 8, максимальное количество символов 16")
    private String newPassword;

    public NewPassword() {
    }

    public String getCurrentPassword() {
        return currentPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
