package ru.skypro.homework.service;

import ru.skypro.homework.dto.Register;

/**
 * Интерфейс для аутентификации пользователей.
 */
public interface AuthService {

    /**
     * Выполняет вход пользователя в систему.
     *
     * @param userName имя пользователя, используемое для входа.
     * @param password пароль пользователя.
     * @return true, если вход выполнен успешно; false в противном случае.
     */
    boolean login(String userName, String password);

    /**
     * Регистрирует нового пользователя в системе.
     *
     * @param register объект, содержащий данные для регистрации нового пользователя.
     * @return true, если регистрация прошла успешно; false в противном случае.
     */
    boolean register(Register register);
}
