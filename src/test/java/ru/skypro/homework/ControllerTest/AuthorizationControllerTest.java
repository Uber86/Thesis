package ru.skypro.homework.ControllerTest;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.skypro.homework.controller.AuthorizationController;
import ru.skypro.homework.dto.Login;
import ru.skypro.homework.service.AuthService;

@ExtendWith(MockitoExtension.class)
public class AuthorizationControllerTest {

    private static final String USERNAME = "username";
    private static final String PASSWORD = "password123";

    @InjectMocks
    private AuthorizationController authorizationController;

    @Mock
    private AuthService authService;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(authorizationController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testLoginSuccess() throws Exception {
        Login login = new Login();
        login.setUsername(USERNAME);
        login.setPassword(PASSWORD);

        when(authService.login(USERNAME, PASSWORD)).thenReturn(true);

        mockMvc.perform(post("/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(login)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(login)));

        verify(authService, times(1)).login(USERNAME, PASSWORD);
    }

    @Test
    public void testLoginUnauthorized() throws Exception {
        Login login = new Login();
        login.setUsername(USERNAME);
        login.setPassword(PASSWORD);

        when(authService.login(USERNAME, PASSWORD)).thenReturn(false);

        mockMvc.perform(post("/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(login)))
                .andExpect(status().isUnauthorized());

        verify(authService, times(1)).login(USERNAME, PASSWORD);
    }
}