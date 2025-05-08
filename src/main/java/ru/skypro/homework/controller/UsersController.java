package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.service.UserService;

import java.io.IOException;
import java.security.Principal;


/**
 * Контроллер для управления пользователями.
 * Предоставляет REST API для работы с информацией о пользователе.
 */
@RestController
@RequestMapping("/users")
@CrossOrigin(value = "http://localhost:3000")
@Tag(name = "Пользователи")
@RequiredArgsConstructor
public class UsersController {

    public final UserService userService;

    /**
     * Устанавливает новый пароль для пользователя.
     *
     * @param newPassword объект, содержащий новый пароль.
     * @return успешное изменение пароля.
     */
    @PostMapping("/set_password")
    public ResponseEntity<Boolean> setPassword(@RequestBody NewPassword newPassword) {
        boolean isUpdated = userService.updatePassword(newPassword);
        if (isUpdated) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    /**
     * Получает информацию о текущем пользователе.
     *
     * @return объект User, содержащим информацию о пользователе.
     */
    @GetMapping("/me")
    @Operation(summary = "Получение информации об авторизованном пользователе")  // Аннотация Swagger
    public ResponseEntity<User> getUserInfo() {
        User user = userService.getCurrentUser();
        return ResponseEntity.ok(user);
    }

    /**
     * Обновляет информацию о текущем пользователе.
     *
     * @param updateUser объект, содержащий обновленные данные пользователя.
     * @return успешное обновление.
     */
    @PatchMapping("/me")
    public ResponseEntity<UpdateUser> updateUserInfo(@RequestBody UpdateUser updateUser) {
        UpdateUser updatedUser = userService.updateUserInfo(updateUser);
        return ResponseEntity.ok(updatedUser);
    }

    /**
     * Обновляет изображение профиля текущего пользователя.
     *
     * @param imageFile файл изображения, который необходимо загрузить.
     * @return ResponseEntity с сообщением об успешном обновлении изображения и кодом состояния 200 (OK).
     */
    @PatchMapping("/me/image")
    public ResponseEntity<byte[]> updateUserImage(
            @Parameter(description = "Файл изображения для обновления", required = true)
            @RequestPart("image") MultipartFile imageFile,
            Authentication authentication) throws IOException {

        String username = authentication.getName();
        byte[] updatedImage = userService.updateUserImage(username, imageFile);

        return ResponseEntity.ok(updatedImage);
    }
}