package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.model.UserModel;

@Mapper (componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {

    UserModel toUserModel(User user);

    User toUserDto(UserModel userModel);
}
