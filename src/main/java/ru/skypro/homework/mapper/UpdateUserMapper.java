package ru.skypro.homework.mapper;

import org.mapstruct.Mapping;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.model.UserModel;

public interface UpdateUserMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "firstName", source = "firstName")
    @Mapping(target = "lastName", source = "lastName")
    @Mapping(target = "phone", source = "phone")
    @Mapping(target = "role", source = "role")
    @Mapping(target = "image", source = "image")
    User toDto(UserModel userModel);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "username", source = "email") // username = email
    @Mapping(target = "firstName", source = "firstName")
    @Mapping(target = "lastName", source = "lastName")
    @Mapping(target = "phone", source = "phone")
    @Mapping(target = "role", source = "role")
    @Mapping(target = "image", source = "image")
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "ads", ignore = true)
    @Mapping(target = "comments", ignore = true)
    UserModel toModel(User user);
}
