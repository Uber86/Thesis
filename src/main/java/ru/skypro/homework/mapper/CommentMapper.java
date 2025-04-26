package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.model.CommentModel;

import java.util.List;

@Mapper (componentModel = MappingConstants.ComponentModel.SPRING)
public interface CommentMapper {

    @Mapping(source = "author.id", target = "author")
    @Mapping(source = "author.image", target = "authorImage")
    @Mapping(source = "author.firstName", target = "authorFirstName")
    @Mapping(source = "createAt", target = "createAt")
    @Mapping(source = "pk", target = "pk")
    @Mapping(source = "text", target = "text")
    Comment toCommentDto(CommentModel commentModel);

    List<Comment> toCommentDtoList(List<CommentModel> commentModelList);

    @Mapping(target = "pk",ignore = true)
    @Mapping(target = "author",ignore = true)
    @Mapping(target = "createAt",ignore = true)
    CommentModel toCommentModel(CreateOrUpdateComment createOrUpdateComment);

}
