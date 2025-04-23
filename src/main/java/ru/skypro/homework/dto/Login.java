package ru.skypro.homework.dto;

import lombok.Data;

import javax.validation.constraints.Size;


/**
 * Класс, представляющий данные для входа в систему (Login).
 * <p>
 * Содержит информацию о имени пользователя и пароле, которые должны соответствовать определенным требованиям.
 * </p>
 */
@Data
public class Login {

    /**
     * Пароль пользователя.
     * <p>
     * Должен содержать от 8 до 16 символов.
     * </p>
     */
    @Size(min = 8, max = 16, message = "Минимальное количество символов 8, максимальное количество символов 16")
    private String password;

    /**
     * Имя пользователя.
     * <p>
     * Должно содержать от 4 до 32 символов.
     * </p>
     */
    @Size(min = 4, max = 32, message = "Минимальное количество символов 4, максимальное количество символов 32")
    private String username;

    public Login() {
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
