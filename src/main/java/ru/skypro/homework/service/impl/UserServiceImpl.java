package ru.skypro.homework.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.exception.UserNotFoundException;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.model.UserModel;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.UserService;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    /**
     * Обновление пароля пользователя
     */
    public boolean updatePassword(String currentPassword, String newPassword) {
        UserModel userModel = userRepository.findById(1L)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        userMapper.updatePasswordFromDto(newPassword, userModel);
        userModel.setPassword(newPassword);
        userRepository.save(userModel);
        return true;
    }

    public User getCurrentUser() {
        Long currentUserId = 1L; // временная заглушка
        return userRepository.findById(currentUserId)
                .map(userMapper::toDto)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    public UpdateUser updateUserInfo(UpdateUser updateUser) {
        Long currentUserId = 1L; // временная заглушка
        UserModel user = userRepository.findById(currentUserId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        if (updateUser.getFirstName() != null) {
            user.setFirstName(updateUser.getFirstName());
        }
        if (updateUser.getLastName() != null) {
            user.setLastName(updateUser.getLastName());
        }
        if (updateUser.getPhone() != null) {
            user.setPhone(updateUser.getPhone());
        }

        UserModel savedUser = userRepository.save(user);

        return userMapper.toUpdateUserDto(savedUser);
    }

    @Override
    public byte[] updateUserImage(String username, MultipartFile imageFile) throws IOException {
        UserModel userModel = userRepository.findByUsername(username);
        byte[] imageBytes = imageFile.getBytes();

        userModel.setImage(Arrays.toString(imageBytes));
        userRepository.save(userModel);

        return imageBytes;
    }

    @Override
    public UserModel getCurrentUserModel(String username) {
        return getUserModelByUsername(username);
    }

    private UserModel getUserModelByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
