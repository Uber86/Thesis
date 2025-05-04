package ru.skypro.homework.dto;

import lombok.Data;


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
    private Role role;

    /**
     * URL изображения профиля пользователя.
     */
    private String image;

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
