package ru.skypro.homework.service;

import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;

public interface UserService {
    boolean updatePassword(String currentPassword, String newPassword);
    User getCurrentUser();
    UpdateUser updateUserInfo(UpdateUser updateUser);


}
