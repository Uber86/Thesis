package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.model.UserModel;

import java.io.IOException;

public interface UserService {
    boolean updatePassword(String currentPassword, String newPassword);
    User getCurrentUser();
    UpdateUser updateUserInfo(UpdateUser updateUser);
    byte[] updateUserImage(String username, MultipartFile imageFile) throws IOException;
    UserModel getCurrentUserModel(String username);

}
