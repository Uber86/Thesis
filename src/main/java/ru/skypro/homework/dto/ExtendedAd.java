package ru.skypro.homework.dto;

import lombok.Data;

/**
 * Класс, представляющий расширенное объявление (ExtendedAd).
 * <p>
 * Содержит информацию об авторе, описании, контактных данных и других характеристиках объявления.
 * </p>
 */
@Data
public class ExtendedAd {

    /**
     * Уникальный первичный ключ объявления.
     */
    private int pk;

    /**
     * Имя автора объявления.
     */
    private String authorFirstName;

    /**
     * Фамилия автора объявления.
     */
    private String authorLastName;

    /**
     * Описание объявления.
     */
    private String description;

    /**
     * Электронная почта автора объявления.
     */
    private String email;

    /**
     * URL или путь к изображению, связанному с объявлением.
     */
    private String image;

    /**
     * Номер телефона автора объявления.
     */
    private String phone;

    /**
     * Цена объявления.
     */
    private int price;

    /**
     * Заголовок объявления.
     */
    private String title;

    public ExtendedAd() {
    }

    public int getPk() {
        return pk;
    }

    public void setPk(int pk) {
        this.pk = pk;
    }

    public String getAuthorFirstName() {
        return authorFirstName;
    }

    public void setAuthorFirstName(String authorFirstName) {
        this.authorFirstName = authorFirstName;
    }

    public String getAuthorLastName() {
        return authorLastName;
    }

    public void setAuthorLastName(String authorLastName) {
        this.authorLastName = authorLastName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
