package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.service.AdService;


/**
 * Контроллер для управления объявлениями.
 * Предоставляет REST API для создания, получения, обновления и удаления объявлений.
 */
@RestController
@CrossOrigin(value = "http://localhost:3000")
@Tag(name = "Объявления")
@RequiredArgsConstructor
public class AdController {

    private final AdService adService;

    /**
     * Получает все объявления.
     *
     * @return объект Ads, содержащим все объявления.
     */
    @GetMapping("/ads")
    public Ads getAllAds() {
        return adService.getAllAds();
    }

    /**
     *  Добавляет новое объявление.
     * @param properties объект объявления, который нужно добавить.
     * @param image изображение
     * @return объект ad
     */
    @PostMapping("/ads")
    public Ad addAd(@RequestPart("properties") CreateOrUpdateAd properties,
                    @RequestPart("image") MultipartFile image) {
        return adService.addAd(properties, image);
    }

    /**
     * Получает объявление по его идентификатору.
     *
     * @param id идентификатор объявления.
     * @return extendedAd, если объявление найдено.
     */
    @GetMapping("/ads/{id}")
    public ExtendedAd getAd(@PathVariable int id) {
        return adService.getAd(id);
    }

    /**
     * Удаляет объявление по его идентификатору.
     *
     * @param id идентификатор объявления, которое нужно удалить.
     */
    @DeleteMapping("/ads/{id}")
    public void deleteAd(@PathVariable int id) {
        adService.deleteAd(id);

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
        return adService.updateAd(id, createOrUpdateAd);
    }

    /**
     * Получает объявления текущего пользователя.
     *
     * @param authentication объект пользователя, который аутентифицирован в системе.
     * @return Объект Ads, содержащим объявления текущего пользователя.
     */
    @GetMapping("/ads/me")
    public Ads getAdsMe(Authentication authentication) {
        String username = ((User) authentication.getPrincipal()).getUsername();
        return adService.getAdsByUserName(username);
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
            @RequestParam("id") int id,
            @RequestParam("image") MultipartFile image) {
        byte[] updatedImage = adService.updateImage(id, image);
        String contentType = image.getContentType();
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .body(updatedImage);
    }
}