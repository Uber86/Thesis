package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.model.UserModel;

import java.util.List;

/**
 * Интерфейс маппера для преобразования объектов User и UserModel.
 * Этот интерфейс использует MapStruct для генерации реализации во время компиляции.
 * Модель компонента установлена в "spring" для возможности внедрения зависимостей Spring.
 */
@Mapper (componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {


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
    @Mapping(target = "firstName", source = "firstName")
    @Mapping(target = "lastName", source = "lastName")
    @Mapping(target = "phone", source = "phone")
    @Mapping(target = "role", source = "role")
    @Mapping(target = "image", source = "image")
    @Mapping(target = "username", source = "email")
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "ads", ignore = true)
    @Mapping(target = "comments", ignore = true)
    UserModel toModel(User user);

    /**
     * Преобразует список объектов UserModel в список DTO User.
     *
     * @param userModels список объектов UserModel для преобразования
     * @return список преобразованных DTO User
     */
    List<User> toDtoList(List<UserModel> userModels);

    /**
     * Преобразует список объектов User в список объектов UserModel.
     *
     * @param users список объектов User для преобразования
     * @return список преобразованных объектов UserModel
     */
    List<UserModel> toModelList(List<User> users);
}
