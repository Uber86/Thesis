package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.Register;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.model.UserModel;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AuthService;

import static ru.skypro.homework.dto.Role.ADMIN;
import static ru.skypro.homework.dto.Role.USER;


@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository repository;

    private final PasswordEncoder encoder;

    private final UserMapper mapper;


    @Override
    public boolean login(String userName, String password) {
        UserModel user = repository.findByUsername(userName);
        return user != null && encoder.matches(password, user.getPassword());
    }

    @Override
    public boolean register(Register register) {
        if (repository.existsByEmail(register.getUsername())) {
            return false;
        }
        if(register.getRole() == ADMIN){
            return false;
        }
        UserModel user = mapper.toUserModel(register);
        user.setEmail(register.getUsername());
        user.setPassword(encoder.encode(register.getPassword()));
        user.setRole(USER);
        repository.save(user);
        return true;
    }
}
