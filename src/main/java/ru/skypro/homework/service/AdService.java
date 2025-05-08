package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;

import java.io.IOException;

/**
 * Интерфейс для работы с объявлениями.
 */
public interface AdService {

    /**
     * Получает все объявления.
     *
     * @return объект Ads, содержащий список всех объявлений.
     */
    Ads getAllAds();

    /**
     * Добавляет новое объявление с изображением.
     *
     * @param properties объект, содержащий данные нового объявления.
     * @param image файл изображения для объявления.
     * @return созданный объект Ad.
     * @throws IOException если произошла ошибка при обработке файла изображения.
     */
    Ad addAd(CreateOrUpdateAd properties, MultipartFile image) throws IOException;

    /**
     * Получает подробную информацию об объявлении по его идентификатору.
     *
     * @param id идентификатор объявления.
     * @return объект ExtendedAd, содержащий подробную информацию об объявлении.
     */
    ExtendedAd getAd(int id);

    /**
     * Удаляет объявление по его идентификатору.
     *
     * @param id идентификатор объявления для удаления.
     */
    void deleteAd(int id);

    /**
     * Обновляет существующее объявление.
     *
     * @param id идентификатор объявления для обновления.
     * @param createOrUpdateAd объект, содержащий обновленные данные объявления.
     * @return обновленный объект Ad.
     */
    Ad updateAd(int id, CreateOrUpdateAd createOrUpdateAd);

    /**
     * Получает все объявления пользователя по его идентификатору.
     *
     * @param userId идентификатор пользователя, чьи объявления нужно получить.
     * @return объект Ads, содержащий список всех объявлений данного пользователя.
     */
    Ads getAdsByUserId(int userId);

    /**
     * Обновляет изображение объявления по его идентификатору.
     *
     * @param id идентификатор объявления, для которого нужно обновить изображение.
     * @param image файл нового изображения для объявления.
     * @return строка с сообщением о результате операции.
     * @throws IOException если произошла ошибка при обработке файла изображения.
     */
    String updateImage(long id, MultipartFile image) throws IOException;

    /**
     * Получает все объявления пользователя по его имени пользователя.
     *
     * @param username имя пользователя, чьи объявления нужно получить.
     * @return объект Ads, содержащий список всех объявлений данного пользователя.
     */
    Ads getAdsByUserName(String username);
}
