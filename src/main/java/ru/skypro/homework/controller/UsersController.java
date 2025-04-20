package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;

import java.util.Arrays;

import static ru.skypro.homework.dto.Role.USER;


@RestController
@Tag(name = "Пользователи")
@RequiredArgsConstructor
public class UsersController {

    @PostMapping("/set_password")
    public ResponseEntity<NewPassword> setPassword (@RequestBody NewPassword newPassword) {
        return ResponseEntity.status(HttpStatus.OK).build();

    }

    @GetMapping("/users/me")
    public ResponseEntity<User> getUserInfo(){
        User user = new User();
        user.setId(0);
        user.setEmail("String");
        user.setFirstName("String");
        user.setLastName("String");
        user.setPhone("String");
        user.setRole(Arrays.asList(USER));
        user.setImage ("String");
        return ResponseEntity.ok(user);
    }

    @PatchMapping("/users/me")
    public ResponseEntity<UpdateUser> updateUserInfo (@RequestBody UpdateUser updateUser) {

        return ResponseEntity.ok(updateUser);
    }

    @PatchMapping("/users/me/image")
    public ResponseEntity<String> updateUserImage(@Parameter(
            description ="Файл изображения для обновления", required = true)
                                                      @RequestParam ("image")
                                                  MultipartFile imageFile) {

        return ResponseEntity.ok(String.valueOf(imageFile));
    }
}
