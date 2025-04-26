package ru.skypro.homework.mapper;

import org.mapstruct.Mapping;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.model.UserModel;

/**
 * Интерфейс маппера для преобразования объектов User и UserModel.
 * Этот интерфейс использует MapStruct для генерации реализации во время компиляции.
 */
public interface UpdateUserMapper {

    /**
     * Преобразует объект UserModel в DTO User.
     *
     * @param userModel объект UserModel для преобразования
     * @return преобразованный DTO User
     */
    @Mapping(target = "id", source = "id")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "firstName", source = "firstName")
    @Mapping(target = "lastName", source = "lastName")
    @Mapping(target = "phone", source = "phone")
    @Mapping(target = "role", source = "role")
    @Mapping(target = "image", source = "image")
    User toDto(UserModel userModel);

    /**
     * Преобразует объект User в объект UserModel.
     * Поля password, ads и comments игнорируются при преобразовании.
     *
     * @param user объект User для преобразования
     * @return преобразованный объект UserModel
     */
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
