package ru.skypro.homework.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.model.UserModel;
import ru.skypro.homework.repository.UserRepository;

import javax.persistence.EntityNotFoundException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserServiceImpl userService;

    private UserModel userModel;


    @BeforeEach
    void setUp() {
        userModel = new UserModel();
        userModel.setUsername("testuser");
        userModel.setFirstName("Test");
        userModel.setLastName("User");
        userModel.setPhone("+7 (999) 999-99-99");


    }

    @Test
    void getUser_Success() {


    }
    @Test
    void getUser_NotFound() {
        when(userRepository.findByUsername("unknown")).thenReturn(null);


    }
}