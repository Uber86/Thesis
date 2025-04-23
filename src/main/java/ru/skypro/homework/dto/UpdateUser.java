package ru.skypro.homework.dto;

import lombok.Data;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Класс, представляющий данные для обновления информации о пользователе (UpdateUser).
 * <p>
 * Содержит информацию об имени, фамилии и номере телефона пользователя,
 * которые должны соответствовать определенным требованиям.
 * </p>
 */
@Data
public class UpdateUser {

    /**
     * Имя пользователя.
     * <p>
     * Должно содержать от 3 до 10 символов.
     * </p>
     */
    @Size(min = 3, max = 10, message = "Минимальное количество символов 3, максимальное количество символов 10")
    private String firstName;

    /**
     * Фамилия пользователя.
     * <p>
     * Должна содержать от 3 до 10 символов.
     * </p>
     */
    @Size(min = 3, max = 10, message = "Минимальное количество символов 3, максимальное количество символов 10")
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

    public UpdateUser() {
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
}
