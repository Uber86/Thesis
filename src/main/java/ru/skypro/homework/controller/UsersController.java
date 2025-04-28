package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;

import java.util.Arrays;

import static ru.skypro.homework.dto.Role.USER;

/**
 * Контроллер для управления пользователями.
 * Предоставляет REST API для работы с информацией о пользователе.
 */
@RestController
@CrossOrigin(value = "http://localhost:3000")
@Tag(name = "Пользователи")
@RequiredArgsConstructor
public class UsersController {

    /**
     * Устанавливает новый пароль для пользователя.
     *
     * @param newPassword объект, содержащий новый пароль.
     * @return успешное изменение пароля.
     */
    @PostMapping("/set_password")
    public NewPassword setPassword(@RequestBody NewPassword newPassword) {
        return new NewPassword();
    }

    /**
     * Получает информацию о текущем пользователе.
     *
     * @return объект User, содержащим информацию о пользователе.
     */
    @GetMapping("/users/me")
    public User getUserInfo() {
        User user = new User();
        return user;
    }

    /**
     * Обновляет информацию о текущем пользователе.
     *
     * @param updateUser объект, содержащий обновленные данные пользователя.
     * @return успешное обновление.
     */
    @PatchMapping("/users/me")
    public UpdateUser updateUserInfo(@RequestBody UpdateUser updateUser) {
        return new UpdateUser();
    }

    /**
     * Обновляет изображение профиля текущего пользователя.
     *
     * @param imageFile файл изображения, который необходимо загрузить.
     * @return ResponseEntity с сообщением об успешном обновлении изображения и кодом состояния 200 (OK).
     */
    @PatchMapping("/users/me/image")
    public ResponseEntity<String> updateUserImage(
            @Parameter(description = "Файл изображения для обновления", required = true)
            @RequestParam("image") MultipartFile imageFile) {

        return ResponseEntity.ok(String.valueOf(imageFile));
    }
}