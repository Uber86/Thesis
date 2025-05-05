package ru.skypro.homework.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.Role;
import ru.skypro.homework.model.UserModel;
import ru.skypro.homework.repository.UserRepository;

import javax.annotation.PostConstruct;
import javax.persistence.Column;

/**
 * Класс AdminInitializer отвечает за инициализацию админа
 * Админ может быть один или несколько человек (для порядка)
 */
@Component
@RequiredArgsConstructor
public class AdminInitializer {

    private final UserRepository repository;

    private final PasswordEncoder encoder;

    @Value("${admin.password}")
    private String adminPassword; // Получаем пароль из проперти

    @PostConstruct
    public void initAdmin() {
        String user = "Admin";
        if (!repository.existsByUsername(user)) {
            UserModel admin = new UserModel();
            admin.setUsername(user);
            admin.setPassword(encoder.encode(adminPassword)); // Используем пароль из проперти
            admin.setRole(Role.ADMIN);
            admin.setEmail("admin@admin.com");
            admin.setFirstName("Admin");
            admin.setLastName("Admin");
            admin.setPhone("+79999999999");
            repository.save(admin);
        }
    }
}
