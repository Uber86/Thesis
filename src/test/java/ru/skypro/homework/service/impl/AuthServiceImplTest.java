package ru.skypro.homework.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthServiceImplTest {

    @Mock
    private UserDetailsManager manager;
    @Mock private PasswordEncoder encoder;
    @InjectMocks
    private AuthServiceImpl authService;

    @Test
    void login_success() {
        String userName = "testUser";
        String password = "password";
        String encodedPassword = "encodedPassword";
        UserDetails userDetails = User.builder()
                .username(userName).password(encodedPassword).roles("USER").build();
        when(manager.userExists(userName)).thenReturn(true);
        when(manager.loadUserByUsername(userName)).thenReturn(userDetails);
        when(encoder.matches(password, encodedPassword)).thenReturn(true);
        assertTrue(authService.login(userName, password));
    }
}

