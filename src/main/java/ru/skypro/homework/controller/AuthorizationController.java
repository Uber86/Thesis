package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import ru.skypro.homework.dto.Login;
import ru.skypro.homework.service.AuthService;

/**
 * Контроллер для управления авторизацией пользователей.
 * Предоставляет REST API для входа в систему.
 */
@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@Tag(name = "Авторизация")
public class AuthorizationController {

    private final AuthService authService;

    /**
     * Выполняет вход пользователя в систему.
     *
     * @param login объект, содержащий имя пользователя и пароль.
     * @return ResponseEntity с кодом состояния 200 (OK), если вход выполнен успешно,
     *         или 401 (Unauthorized), если учетные данные неверны.
     */
    @PostMapping("/login")
    public Login login(@RequestBody Login login) {
        if (authService.login(login.getUsername(), login.getPassword())) {
            return login;
        } else {
            throw new
                    ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
    }
}

