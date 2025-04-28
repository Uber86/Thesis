package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.model.CommentModel;

import java.util.List;

/**
 * Интерфейс маппера для преобразования объектов Comment и CommentModel.
 * Этот интерфейс использует MapStruct для генерации реализации во время компиляции.
 * Модель компонента установлена в "spring" для возможности внедрения зависимостей Spring.
 */
@Mapper (componentModel = MappingConstants.ComponentModel.SPRING)
public interface CommentMapper {

    /**
     * Преобразует объект CommentModel в DTO Comment.
     *
     * @param commentModel объект CommentModel для преобразования
     * @return преобразованный DTO Comment
     */
    @Mapping(source = "author.id", target = "author")
    @Mapping(source = "author.image", target = "authorImage")
    @Mapping(source = "author.firstName", target = "authorFirstName")
    @Mapping(source = "createAt", target = "createAt")
    @Mapping(source = "pk", target = "pk")
    @Mapping(source = "text", target = "text")
    Comment toCommentDto(CommentModel commentModel);

    /**
     * Преобразует список объектов CommentModel в список DTO Comment.
     *
     * @param commentModelList список объектов CommentModel для преобразования
     * @return список преобразованных DTO Comment
     */
    List<Comment> toCommentDtoList(List<CommentModel> commentModelList);

    /**
     * Преобразует объект CreateOrUpdateComment в объект CommentModel.
     * Поля pk, author и createAt игнорируются при преобразовании.
     *
     * @param createOrUpdateComment объект CreateOrUpdateComment для преобразования
     * @return преобразованный объект CommentModel
     */
    @Mapping(target = "pk",ignore = true)
    @Mapping(target = "author",ignore = true)
    @Mapping(target = "createAt",ignore = true)
    CommentModel toCommentModel(CreateOrUpdateComment createOrUpdateComment);

}
