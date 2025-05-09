package ru.skypro.homework.dto;

import lombok.Data;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;


/**
 * Класс, представляющий данные для регистрации пользователя (Register).
 * <p>
 * Содержит информацию об имени пользователя, пароле, имени, фамилии и номере телефона,
 * которые должны соответствовать определенным требованиям.
 * </p>
 */
@Data
public class Register {

    /**
     * Имя пользователя.
     * <p>
     * Должно содержать от 4 до 32 символов.
     * </p>
     */
    @Size(min = 4, max = 32, message = "Минимальное количество символов 4, максимальное количество символов 32")
    private String username;

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
     * Должно содержать от 2 до 16 символов.
     * </p>
     */
    @Size(min = 2, max = 16, message = "Минимальное количество символов 2, максимальное количество символов 16")
    private String firstName;

    /**
     * Фамилия пользователя.
     * <p>
     * Должна содержать от 2 до 16 символов.
     * </p>
     */
    @Size(min = 2, max = 16, message = "Минимальное количество символов 2," +
            " максимальное количество символов 16")
    private String lastName;

    /**
     * Номер телефона пользователя.
     * <p>
     * Должен соответствовать формату: +7 (XXX) XXX-XX-XX
     * </p>
     */
    @Pattern(regexp = "\\+7\\s?\\(?\\d{3}\\)?\\s?\\d{3}-?\\d{2}-?\\d{2}",
    message = "Неверный формат номера телефона")
    private String phone;
    /**
     * Список ролей пользователя.
     */
    private List<Role> role;

    public Register() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Role> getRole() {
        return role;
    }

    public void setRole(List<Role> role) {
        this.role = role;
    }
}
