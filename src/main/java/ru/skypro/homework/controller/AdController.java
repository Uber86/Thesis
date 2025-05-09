package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.service.AdService;

import java.io.IOException;


/**
 * Контроллер для управления объявлениями.
 * Предоставляет REST API для создания, получения, обновления и удаления объявлений.
 */
@RestController
@RequestMapping("/ads")
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
    @GetMapping
    public Ads getAllAds() {
        return adService.getAllAds();
    }

    /**
     *  Добавляет новое объявление.
     * @param properties объект объявления, который нужно добавить.
     * @param image изображение
     * @return объект ad
     */
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Ad addAd(@RequestPart("properties") CreateOrUpdateAd properties,
                    @RequestPart("image") MultipartFile image) throws IOException {
        return adService.addAd(properties, image);
    }

    /**
     * Получает объявление по его идентификатору.
     *
     * @param id идентификатор объявления.
     * @return extendedAd, если объявление найдено.
     */
    @GetMapping("/{id}")
    public ExtendedAd getAd(@PathVariable int id) {
        return adService.getAd(id);
    }

    /**
     * Удаляет объявление по его идентификатору.
     *
     * @param id идентификатор объявления, которое нужно удалить.
     */
    @DeleteMapping("/{id}")
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
    @PatchMapping("/{id}")
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
    @GetMapping("/me")
    public Ads getAdsMe(Authentication authentication) {
        String username = authentication.getName();
        return adService.getAdsByUserName(username);
    }

    /**
     * Обновляет изображение объявления.
     *
     * @param id идентификатор объявления, для которого нужно обновить изображение.
     * @param imageFile файл изображения, который нужно загрузить.
     * @return ResponseEntity с байтовым массивом изображения.
     */
    @PatchMapping(value = "/{id}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> updateImage(
            @PathVariable("id") int id,
            @RequestParam("image") MultipartFile imageFile) throws IOException {
        String imageId = adService.updateImage(id, imageFile);
        return ResponseEntity.ok("/images/" + imageId);
    }

    @GetMapping("/image/{id}")
    public ResponseEntity<byte[]> getImage(@PathVariable String id) {
        try {
            byte[] imageBytes = adService.getImage(id);
            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG) // Укажите правильный тип контента
                    .body(imageBytes);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}