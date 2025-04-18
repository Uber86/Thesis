package ru.skypro.homework.service;

import ru.skypro.homework.dto.Login;
import ru.skypro.homework.dto.Register;
import ru.skypro.homework.dto.UpdateUser;

public interface AuthService {
    boolean login(String userName, String password);

    boolean register(Register register);

    boolean setPassword(String userName,  String currentPassword, String newPassword);

    Register getUserRegisterInfo(String username);
}
