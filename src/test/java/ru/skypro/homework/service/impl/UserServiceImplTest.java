package ru.skypro.homework.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.exception.UserNotFoundException;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.model.UserModel;
import ru.skypro.homework.security.UsersDetailsService;
import ru.skypro.homework.repository.UserRepository;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(org.mockito.junit.jupiter.MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @Mock
    private UsersDetailsService usersDetailsService;

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private MultipartFile multipartFile;

    @Mock
    private SecurityContext securityContext;

    @Mock
    private Authentication authentication;

    private UserModel userModel;
    private User userDto;
    private UpdateUser updateUserDto;

    @BeforeEach
    void setUp() {
        SecurityContextHolder.setContext(securityContext);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.isAuthenticated()).thenReturn(true);
        when(authentication.getName()).thenReturn("testuser");

        userModel = new UserModel();
        userModel.setId(1L);
        userModel.setUsername("testuser");
        userModel.setFirstName("Иван");
        userModel.setLastName("Иванов");
        userModel.setPhone("+7 (999) 999-99-99");
        userModel.setPassword("oldPassword");

        userDto = new User();
        userDto.setFirstName("Иван");
        userDto.setLastName("Иванов");
        userDto.setPhone("+7 (999) 999-99-99");

        updateUserDto = new UpdateUser();
        updateUserDto.setFirstName("Пётр");
        updateUserDto.setLastName("Петров");
        updateUserDto.setPhone("+7 (888) 888-88-88");
    }

    @Test
    void getCurrentUser_shouldReturnUserDto() {
        when(usersDetailsService.loadUserByUsername("testuser")).thenReturn((UserDetails) userModel);
        when(userRepository.findById(1L)).thenReturn(Optional.of(userModel));
        when(userMapper.toDto(userModel)).thenReturn(userDto);

        User result = userService.getCurrentUser();

        assertNotNull(result);
        assertEquals("Иван", result.getFirstName()); // вместо getUsername() используем getFirstName()
    }

    @Test
    void updateUserInfo_shouldUpdateAndReturnUpdateUserDto() {
        when(usersDetailsService.loadUserByUsername("testuser")).thenReturn((UserDetails) userModel);
        when(userRepository.findById(1L)).thenReturn(Optional.of(userModel));
        when(userRepository.save(userModel)).thenReturn(userModel);
        when(userMapper.toUpdateUserDto(userModel)).thenReturn(updateUserDto);

        UpdateUser result = userService.updateUserInfo(updateUserDto);

        assertNotNull(result);
        assertEquals("Пётр", userModel.getFirstName());
        assertEquals("Петров", userModel.getLastName());
        assertEquals("+7 (888) 888-88-88", userModel.getPhone());
        verify(userRepository).save(userModel);
        verify(userMapper).toUpdateUserDto(userModel);
    }

    @Test
    void updatePassword_shouldUpdatePassword() {
        when(usersDetailsService.loadUserByUsername("testuser")).thenReturn((UserDetails) userModel);
        when(userRepository.findById(1L)).thenReturn(Optional.of(userModel));
        boolean result = userService.updatePassword("oldPassword", "newPassword");

        assertTrue(result);
        assertEquals("newPassword", userModel.getPassword());
        verify(userRepository).save(userModel);
        verify(userMapper).updatePasswordFromDto("newPassword", userModel);
    }

    @Test
    void updateUserImage_shouldSaveImage() throws IOException {
        byte[] imageBytes = "image data".getBytes();

        when(userRepository.findByUsername("testuser")).thenReturn(userModel);
        when(multipartFile.getBytes()).thenReturn(imageBytes);
        when(userRepository.save(userModel)).thenReturn(userModel);

        byte[] result = userService.updateUserImage("testuser", multipartFile);

        assertArrayEquals(imageBytes, result);
        verify(userRepository).save(userModel);
        assertTrue(userModel.getImage().contains("image data"));
    }

    @Test
    void getCurrentUser_whenUserNotFound_shouldThrow() {
        when(usersDetailsService.loadUserByUsername("testuser")).thenReturn((UserDetails) userModel);
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userService.getCurrentUser());
    }
}