package ru.skypro.homework.ControllerTest;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.skypro.homework.controller.UsersController;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.service.UserService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class UsersControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @InjectMocks
    private UsersController usersController;

    @Test
    @WithMockUser
    void setPassword_ShouldReturnOk_WhenPasswordUpdated() throws Exception {
        NewPassword newPassword = new NewPassword();
        newPassword.setCurrentPassword("oldPass");
        newPassword.setNewPassword("newPass");

        when(userService.updatePassword(any(NewPassword.class))).thenReturn(true);

        mockMvc.perform(post("/users/set_password")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"currentPassword\":\"oldPass\",\"newPassword\":\"newPass\"}"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    void setPassword_ShouldReturnForbidden_WhenPasswordNotUpdated() throws Exception {
        NewPassword newPassword = new NewPassword();
        newPassword.setCurrentPassword("wrongPass");
        newPassword.setNewPassword("newPass");

        when(userService.updatePassword(any(NewPassword.class))).thenReturn(false);

        mockMvc.perform(post("/users/set_password")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"currentPassword\":\"wrongPass\",\"newPassword\":\"newPass\"}"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser
    void getUserInfo_ShouldReturnUserInfo() throws Exception {
        User user = new User();
        user.setEmail("test@example.com");
        user.setFirstName("Test");
        user.setLastName("User");

        when(userService.getCurrentUser()).thenReturn(user);

        mockMvc.perform(get("/users/me"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("test@example.com"))
                .andExpect(jsonPath("$.firstName").value("Test"))
                .andExpect(jsonPath("$.lastName").value("User"));
    }

    @Test
    @WithMockUser
    void updateUserInfo_ShouldReturnUpdatedUser() throws Exception {
        UpdateUser updateUser = new UpdateUser();
        updateUser.setFirstName("Updated");
        updateUser.setLastName("User");
        updateUser.setPhone("+79998887766");

        when(userService.updateUserInfo(any(UpdateUser.class))).thenReturn(updateUser);

        mockMvc.perform(patch("/users/me")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"firstName\":\"Updated\",\"lastName\":\"User\",\"phone\":\"+79998887766\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("Updated"))
                .andExpect(jsonPath("$.lastName").value("User"))
                .andExpect(jsonPath("$.phone").value("+79998887766"));
    }
}