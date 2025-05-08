package ru.skypro.homework.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.model.UserModel;
import ru.skypro.homework.repository.UserRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AuthServiceTest {

    @Mock
    private UserRepository repository;

    @Mock
    private PasswordEncoder encoder;

    @Mock
    private UserMapper mapper;

    @InjectMocks
    private AuthServiceImpl authService;

    @Test
    void login_success() {
        String userName = "test@example.com";
        String password = "password";
        String encodedPassword = "encodedPassword";

        UserModel user = new UserModel();
        user.setEmail(userName);
        user.setPassword(encodedPassword);

        when(repository.findByUsername(userName)).thenReturn(user);
        when(encoder.matches(password, encodedPassword)).thenReturn(true);

        assertTrue(authService.login(userName, password));
    }

    @Test
    void login_userNotFound_returnsFalse() {
        String userName = "nonexistent@example.com";
        String password = "password";

        when(repository.findByUsername(userName)).thenReturn(null);

        assertFalse(authService.login(userName, password));
    }

    @Test
    void login_wrongPassword_returnsFalse() {
        String userName = "test@example.com";
        String password = "wrongPassword";
        String encodedPassword = "encodedPassword";

        UserModel user = new UserModel();
        user.setEmail(userName);
        user.setPassword(encodedPassword);

        when(repository.findByUsername(userName)).thenReturn(user);
        when(encoder.matches(password, encodedPassword)).thenReturn(false);

        assertFalse(authService.login(userName, password));
    }
}