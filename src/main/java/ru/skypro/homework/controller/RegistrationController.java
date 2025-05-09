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
import ru.skypro.homework.dto.Register;
import ru.skypro.homework.service.AuthService;

/**
 * Контроллер для управления процессом регистрации пользователей.
 * Предоставляет REST API для регистрации новых пользователей.
 */
@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@Tag(name = "Регистрация")
public class RegistrationController {

    private final AuthService authService;

    /**
     * Регистрация нового пользователя.
     *
     * @param register объект, содержащий данные для регистрации пользователя.
     * @return Регистрация прошла успешно,
     *         или 400 (Bad Request), если произошла ошибка при регистрации.
     */
    @PostMapping("/register")
    public Register register(@RequestBody Register register) {
        if (authService.register(register)) {
            return register;
        } else {
            throw new
                    ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
}