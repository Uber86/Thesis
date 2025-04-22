package ru.skypro.homework.dto;

import lombok.Data;

import java.util.List;

/**
 * Класс, представляющий пользователя в системе (User).
 * <p>
 * Содержит информацию о пользователе, включая идентификатор, электронную почту,
 * имя, фамилию, номер телефона, роли и изображение профиля.
 * </p>
 */
@Data
public class User {

    /**
     * Уникальный идентификатор пользователя.
     */
    private int id;

    /**
     * Электронная почта пользователя.
     */
    private String email;

    /**
     * Имя пользователя.
     */
    private String firstName;

    /**
     * Фамилия пользователя.
     */
    private String lastName;

    /**
     * Номер телефона пользователя.
     */
    private String phone;

    /**
     * Список ролей пользователя.
     */
    private List<Role> role;

    /**
     * URL изображения профиля пользователя.
     */
    private String image;
}
