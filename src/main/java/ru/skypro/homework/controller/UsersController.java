package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;

import java.util.Arrays;

import static ru.skypro.homework.dto.Role.USER;



@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UsersController {

    @PostMapping("/set_password")
    public ResponseEntity<NewPassword> setPassword (@RequestBody NewPassword currentPassword,
                                                    @RequestBody NewPassword newPassword) {
        if (currentPassword.equals(newPassword)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } else{
            return ResponseEntity.status(HttpStatus.OK).build();
        }
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
    public ResponseEntity<UpdateUser> updateUserInfo (@RequestBody String firstName,
                                                  @RequestBody String lastName,
                                                  @RequestBody String phone) {
        UpdateUser updateUser = new UpdateUser();
        updateUser.setFirstName(firstName);
        updateUser.setLastName(lastName);
        updateUser.setPhone(phone);
        return ResponseEntity.ok(updateUser);
    }

    @PatchMapping("/users/me/image")
    public ResponseEntity<User> updateUserImage(@RequestBody String imege) {
        User user = new User();
        user.setImage(imege);
        return ResponseEntity.ok().build();
    }
}
