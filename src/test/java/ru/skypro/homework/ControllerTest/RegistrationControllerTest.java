package ru.skypro.homework.ControllerTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.skypro.homework.controller.RegistrationController;
import ru.skypro.homework.dto.Register;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class RegistrationControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private RegistrationController registrationController;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(registrationController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testRegisterSuccess() throws Exception {
        Register register = new Register();
        register.setUsername("validUser");
        register.setPassword("validPass123");
        register.setFirstName("John");
        register.setLastName("Doe");
        register.setPhone("+7 123 456-78-90");

        mockMvc.perform(post("/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(register)))
                .andExpect(status().isCreated());
    }

    @Test
    public void testRegisterFailure() throws Exception {
        Register register = new Register();
        register.setUsername("short");
        register.setPassword("123");
        register.setFirstName("J");
        register.setLastName("D");
        register.setPhone("invalidPhone");

        mockMvc.perform(post("/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(register)))
                .andExpect(status().isBadRequest());
    }
}
