package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.Register;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.model.UserModel;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AuthService;



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
        UserModel user = mapper.toUserModel(register);

        user.setPassword(encoder.encode(register.getPassword()));
        repository.save(user);
        return true;
    }
}
