package ru.skypro.homework.dto;

import lombok.Data;

/**
 * Класс, представляющий объявление (Ad).
 * <p>
 * Содержит информацию об авторе, изображении, уникальном идентификаторе,
 * цене и заголовке объявления.
 * </p>
 */
@Data
public class Ad extends ExtendedAd {

    /**
     * Идентификатор автора объявления.
     */
    private int author;

    /**
     * URL или путь к изображению объявления.
     */
    private String image;

    /**
     * Уникальный первичный ключ объявления.
     */
    private int pk;

    /**
     * Цена товара или услуги в объявлении.
     */
    private int price;

    /**
     * Заголовок объявления.
     */
    private String title;

    public Ad() {
    }

    public int getAuthor() {
        return author;
    }

    public void setAuthor(int author) {
        this.author = author;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = "/ads/image/" + image;
    }

    public int getPk() {
        return pk;
    }

    public void setPk(int pk) {
        this.pk = pk;
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