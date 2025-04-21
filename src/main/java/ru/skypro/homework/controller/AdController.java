package ru.skypro.homework.controller;

import com.sun.security.auth.UserPrincipal;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.CreateOrUpdateAd;

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
     * @return ResponseEntity с объектом Ads, содержащим все объявления.
     */
    @GetMapping("/ads")
    public ResponseEntity<Ads> getAllAds() {
        return ResponseEntity.ok().build();
    }

    /**
     * Добавляет новое объявление.
     *
     * @param ad объект объявления, который нужно добавить.
     * @return ResponseEntity с добавленным объектом Ad.
     */
    @PostMapping("/ads")
    public ResponseEntity<Ad> addAd(@RequestBody Ad ad) {
        return ResponseEntity.ok().build();
    }

    /**
     * Получает объявление по его идентификатору.
     *
     * @param id идентификатор объявления.
     * @return ResponseEntity с объектом Ad, если объявление найдено.
     */
    @GetMapping("/ads/{id}")
    public ResponseEntity<Ad> getAd(@PathVariable Integer id) {
        return ResponseEntity.ok().build();
    }

    /**
     * Удаляет объявление по его идентификатору.
     *
     * @param id идентификатор объявления, которое нужно удалить.
     * @return ResponseEntity с удаленным объектом Ad.
     */
    @DeleteMapping("/ads/{id}")
    public ResponseEntity<Ad> deleteAd(@PathVariable Integer id) {
        return ResponseEntity.ok().build();
    }

    /**
     * Обновляет существующее объявление.
     *
     * @param id идентификатор объявления, которое нужно обновить.
     * @param createOrUpdateAd объект с новыми данными для обновления объявления.
     * @return ResponseEntity с обновленным объектом Ad.
     */
    @PatchMapping("/ads/{id}")
    public ResponseEntity<Ad> updateAd(
            @PathVariable("id") Integer id,
            @RequestBody CreateOrUpdateAd createOrUpdateAd) {
        return ResponseEntity.ok().build();
    }

    /**
     * Получает объявления текущего пользователя.
     *
     * @param userPrincipal объект пользователя, который аутентифицирован в системе.
     * @return ResponseEntity с объектом Ads, содержащим объявления текущего пользователя.
     */
    @GetMapping("/ads/me")
    public ResponseEntity<Ads> getAdsMe(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        return ResponseEntity.ok().build();
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
            @PathVariable("id") Integer id,
            @RequestParam("image") MultipartFile image) {
        return ResponseEntity.ok().build();
    }
}