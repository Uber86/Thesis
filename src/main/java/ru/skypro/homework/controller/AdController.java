package ru.skypro.homework.controller;

import com.sun.security.auth.UserPrincipal;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;

import java.util.List;

/**
 * Контроллер для управления объявлениями.
 * Предоставляет REST API для создания, получения, обновления и удаления объявлений.
 */
@RestController
@Tag(name = "Объявления")
@RequiredArgsConstructor
public class AdController {

    /**
     * Получает все объявления.
     *
     * @return объект Ads, содержащим все объявления.
     */
    @GetMapping("/ads")
    public Ads getAllAds() {
        Ads ads = new Ads();
        return ads;
    }

    /**
     *  Добавляет новое объявление.
     * @param properties объект объявления, который нужно добавить.
     * @param image изображение
     * @return объект ad
     */
    @PostMapping("/ads")
    public Ad addAd(@RequestParam("properties") CreateOrUpdateAd properties,
                    @RequestParam("image") MultipartFile image) {
        Ad ad = new Ad();
        return ad;
    }

    /**
     * Получает объявление по его идентификатору.
     *
     * @param id идентификатор объявления.
     * @return extendedAd, если объявление найдено.
     */
    @GetMapping("/ads/{id}")
    public ExtendedAd getAd(@PathVariable int id) {
        ExtendedAd extendedAd = new ExtendedAd();
        extendedAd.getPk();
        extendedAd.setAuthorFirstName("String");
        extendedAd.setAuthorLastName("String");
        extendedAd.setDescription("String");
        extendedAd.setEmail("String");
        extendedAd.setImage("String");
        extendedAd.setPhone("String");
        extendedAd.getPrice();
        extendedAd.setTitle("String");
        return extendedAd;
    }

    /**
     * Удаляет объявление по его идентификатору.
     *
     * @param id идентификатор объявления, которое нужно удалить.
     */
    @DeleteMapping("/ads/{id}")
    public void deleteAd(@PathVariable int id) {

    }

    /**
     * Обновляет существующее объявление.
     *
     * @param id идентификатор объявления, которое нужно обновить.
     * @param createOrUpdateAd объект с новыми данными для обновления объявления.
     * @return Обновленный объект Ad.
     */
    @PatchMapping("/ads/{id}")
    public Ad updateAd(
            @PathVariable("id") int id,
            @RequestBody CreateOrUpdateAd createOrUpdateAd) {
        Ad ad = new Ad();
        return ad;
    }

    /**
     * Получает объявления текущего пользователя.
     *
     * @param userPrincipal объект пользователя, который аутентифицирован в системе.
     * @return Объект Ads, содержащим объявления текущего пользователя.
     */
    @GetMapping("/ads/me")
    public Ads getAdsMe(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        Ads ads = new Ads();

        return ads;
    }

    /**
     * Обновляет изображение объявления.
     *
     * @param id идентификатор объявления, для которого нужно обновить изображение.
     * @param image файл изображения, который нужно загрузить.
     * @return ResponseEntity с байтовым массивом изображения.
     */
    @PatchMapping("/ads/{id}/image")
    public ResponseEntity<byte[]> updateImage(
            @PathVariable("id") int id,
            @RequestParam("image") MultipartFile image) {
        return ResponseEntity.ok().build();
    }
}