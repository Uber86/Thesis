package ru.skypro.homework.service.impl;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.exception.UserNotFoundException;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.model.Image;
import ru.skypro.homework.model.UserModel;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.security.UsersDetailsService;
import ru.skypro.homework.service.UserService;

import java.io.IOException;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final UsersDetailsService usersDetailsService;
    private final ImageServiceImpl imageService;

    public UserServiceImpl(UserRepository userRepository,
                           UserMapper userMapper,
                           UsersDetailsService usersDetailsService,
                           ImageServiceImpl imageService) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.usersDetailsService = usersDetailsService;
        this.imageService = imageService;
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

    /**
     * Обновление пароля пользователя
     */
    @Override
    @Transactional
    public boolean updatePassword(NewPassword newPassword) {
        Long currentUserId = getCurrentUserId();
        UserModel userModel = userRepository.findById(currentUserId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        // Проверяем, совпадает ли текущий пароль с сохранённым паролем
        if (!passwordEncoder.matches(newPassword.getCurrentPassword(), userModel.getPassword())) {
            return false; // Текущий пароль не совпадает
        }

        // Кодируем новый пароль
        String encodedNewPassword = passwordEncoder.encode(newPassword.getNewPassword());

        // Обновляем пароль пользователя
        userModel.setPassword(encodedNewPassword);
        userRepository.save(userModel);

        return true; // Пароль успешно обновлён
    }

    @Override
    @Transactional
    public User getCurrentUser() {
        Long currentUserId = getCurrentUserId();
        return userRepository.findById(currentUserId)
                .map(userMapper::toDto)
                .orElseThrow(() -> new UserNotFoundException("User" + currentUserId + " not found"));
    }

    @Override
    @Transactional
    public UpdateUser updateUserInfo(UpdateUser updateUser) {
        Long currentUserId = getCurrentUserId();
        UserModel user = userRepository.findById(currentUserId)
                .orElseThrow(() -> new UserNotFoundException("User" + currentUserId + " not found"));
        if (updateUser.getFirstName() != null &&
                updateUser.getLastName() != null &&
                updateUser.getPhone() != null) {
            user.setFirstName(updateUser.getFirstName());
            user.setLastName(updateUser.getLastName());
            user.setPhone(updateUser.getPhone());
        }
        UserModel savedUser = userRepository.save(user);
        return userMapper.toUpdateUserDto(savedUser);
    }

    @Override
    @Transactional
    public String updateUserImage(String username, MultipartFile imageFile) throws IOException {
        UserModel userModel = userRepository.findByUsername(username);
        Image image = imageService.saveImage(imageFile);
        userModel.setImage(image.getId());
        userRepository.save(userModel);
        return image.getId();
    }
}
