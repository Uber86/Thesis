package ru.skypro.homework.dto;

import lombok.Data;

import javax.validation.constraints.Size;

/**
 * Класс, представляющий данные для создания или обновления объявления (CreateOrUpdateAd).
 * <p>
 * Содержит информацию о заголовке, цене и описании объявления.
 * </p>
 */
@Data
public class CreateOrUpdateAd {

    /**
     * Заголовок объявления.
     * <p>
     * Должен содержать от 4 до 32 символов.
     * </p>
     */
    @Size(min = 4, max = 32, message = "Минимальное количество символов 4, максимальное количество символов 32")
    private String title;

    /**
     * Цена объявления.
     * <p>
     * Должна быть не больше 10,000,000.
     * </p>
     */
    @Size(max = 10_000_000, message = "Максимальная цена 10000000")
    private int price;

    /**
     * Описание объявления.
     * <p>
     * Должно содержать от 8 до 64 символов.
     * </p>
     */
    @Size(min = 8, max = 64, message = "Минимальное количество символов 8, максимальное количество символов 64")
    private String description;

    public CreateOrUpdateAd() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
