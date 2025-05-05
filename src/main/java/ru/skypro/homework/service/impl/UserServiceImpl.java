package ru.skypro.homework.service.impl;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.exception.UserNotFoundException;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.model.UserModel;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.security.UsersDetailsService;
import ru.skypro.homework.service.UserService;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final UsersDetailsService usersDetailsService;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, UsersDetailsService usersDetailsService) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.usersDetailsService = usersDetailsService;
    }

    /**
     * Обновление пароля пользователя
     */
    public boolean updatePassword(String currentPassword, String newPassword) {
        Long currentUserId = getCurrentUserId();
        UserModel userModel = userRepository.findById(currentUserId)
                .orElseThrow(() -> new UserNotFoundException("User" + currentUserId + " not found"));
        if (!userModel.getPassword().equals(currentPassword)) {
            throw new IllegalArgumentException(
                    "Неверный текущий пароль для пользователя " + userModel.getUsername());
        }
        userMapper.updatePasswordFromDto(newPassword, userModel);
        userModel.setPassword(newPassword);
        userRepository.save(userModel);
        return true;
    }

    public User getCurrentUser() {
        Long currentUserId = getCurrentUserId();
        return userRepository.findById(currentUserId)
                .map(userMapper::toDto)
                .orElseThrow(() -> new UserNotFoundException("User" + currentUserId + " not found"));
    }

    public UpdateUser updateUserInfo(UpdateUser updateUser) {
        Long currentUserId = getCurrentUserId();
        UserModel user = userRepository.findById(currentUserId)
                .orElseThrow(() -> new UserNotFoundException("User" + currentUserId + " not found"));

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

    private UserModel getUserModelByUsername(String username) {
        UserModel user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UserNotFoundException("User '" + username + "' not founded");
        }
        return user;
    }

    private Long getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            String username = authentication.getName();
            UserDetails userDetails = usersDetailsService.loadUserByUsername(username);
            UserModel user = userRepository.findByUsername(userDetails.getUsername());
            if (user == null){
                throw new UserNotFoundException("User not found");
            }
            return user.getId();
        }

        throw new UserNotFoundException("No authenticated user found");
    }
}
