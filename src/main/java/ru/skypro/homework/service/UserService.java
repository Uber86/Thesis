package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;

import java.io.IOException;

public interface UserService {
    boolean updatePassword(NewPassword newPassword);
    User getCurrentUser();
    UpdateUser updateUserInfo(UpdateUser updateUser);
    String updateUserImage(String username, MultipartFile imageFile) throws IOException;

}
