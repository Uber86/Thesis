package ru.skypro.homework.service.impl;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.Login;
import ru.skypro.homework.dto.Register;
import ru.skypro.homework.dto.Role;
import ru.skypro.homework.service.AuthService;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserDetailsManager manager;
    private final PasswordEncoder encoder;

    public AuthServiceImpl(UserDetailsManager manager,
                           PasswordEncoder passwordEncoder) {
        this.manager = manager;
        this.encoder = passwordEncoder;
    }

    @Override
    public boolean login(String userName, String password) {
        if (!manager.userExists(userName)) {
            return false;
        }
        UserDetails userDetails = manager.loadUserByUsername(userName);
        return encoder.matches(password, userDetails.getPassword());
    }

    @Override
    public boolean register(Register register) {
        if (manager.userExists(register.getUsername())) {
            return false;
        }
        manager.createUser(
                User.builder()
                        .passwordEncoder(this.encoder::encode)
                        .password(register.getPassword())
                        .username(register.getUsername())
                        .roles(register.getRole().toString())
                        .build());
        return true;
    }

    @Override
    public boolean setPassword(String userName,  String currentPassword, String newPassword) {
        if (!manager.userExists(userName)) {
            return false;
        }
        UserDetails user = manager.loadUserByUsername(userName);
        if (!encoder.matches(currentPassword, user.getPassword())) {
            return false;
        }
        manager.changePassword(
                currentPassword,
                encoder.encode(newPassword)
        );
        return true;
    }

    @Override
    public Register getUserRegisterInfo(String username) {
        UserDetails userDetails = manager.loadUserByUsername(username);

        Register register = new Register();
        register.setUsername(userDetails.getUsername());

        Set<Role> roles = userDetails.getAuthorities().stream()
                .map(auth -> Role.valueOf(auth.getAuthority().replace("ROLE_", "")))
                .collect(Collectors.toSet());
        register.setRole(roles);

        return register;

    }



}
