package ru.skypro.homework.ControllerTest;

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
import ru.skypro.homework.controller.RegistrationController;
import ru.skypro.homework.dto.Register;
import ru.skypro.homework.service.AuthService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)  // <-- Добавлено
public class RegistrationControllerTest {

    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Mock
    private AuthService authService;

    @InjectMocks
    private RegistrationController registrationController;

    @BeforeEach
    public void setUp() {
        // MockitoAnnotations.openMocks(this);  // <-- Удалено, теперь не нужно
        mockMvc = MockMvcBuilders.standaloneSetup(registrationController).build();
    }

    @Test
    public void testRegisterSuccess() throws Exception {
        Register register = new Register();
        register.setUsername("validUser");
        register.setPassword("validPass123");
        register.setFirstName("John");
        register.setLastName("Doe");
        register.setPhone("+7 123 456-78-90");

        when(authService.register(any(Register.class))).thenReturn(true);

        mockMvc.perform(post("/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(register)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(register)));
    }

    @Test
    public void testRegisterFailure() throws Exception {
        Register register = new Register();
        register.setUsername("invalidUser");
        register.setPassword("short");
        register.setFirstName("J");
        register.setLastName("D");
        register.setPhone("invalidPhone");

        when(authService.register(any(Register.class))).thenReturn(false);

        mockMvc.perform(post("/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(register)))
                .andExpect(status().isBadRequest());
    }
}