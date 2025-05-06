package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.Comments;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.exception.UserNotFoundException;
import ru.skypro.homework.mapper.CommentMapper;
import ru.skypro.homework.model.AdModel;
import ru.skypro.homework.model.CommentModel;
import ru.skypro.homework.model.UserModel;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.security.UsersDetailsService;
import ru.skypro.homework.service.CommentService;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    private final CommentMapper mapper;

    private final AdRepository adRepository;

    private final UserRepository userRepository;

    private final UsersDetailsService usersDetailsService;

    private String getCurrentUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new RuntimeException("User is not authenticated");
        }
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return userDetails.getUsername();
    }


    @Override
    public Comments getAllCommentsByAdId(int idAd) {
        List<CommentModel> comment = commentRepository.findByAd((long) idAd);

        List<Comment> toCommentDtoList = mapper.toCommentDtoList(comment);

        Comments comments = new Comments();
        comments.setCount(toCommentDtoList.size());
        comments.setComments(toCommentDtoList);

        return comments;
    }

    @Override
    public Comment createNewComment(int idAd, CreateOrUpdateComment createComment) {
        AdModel adModel = findEntityByIdOrThrow(adRepository, (long) idAd, Ad.class);

        String username = getCurrentUsername();
        UserModel author = userRepository.findByUsername(username);
        if (author == null) {
            throw new EntityNotFoundException("User entity not found");
        }

        CommentModel commentModel = mapper.toCommentModel(createComment);
        commentModel.setCreateAt(LocalDateTime.now());
        commentModel.setAd(adModel);
        commentModel.setAuthor(author);

        CommentModel savedComment = commentRepository.save(commentModel);
        return mapper.toCommentDto(savedComment);
    }

    private <T> T findEntityByIdOrThrow(JpaRepository<T, Long> repository, Long id, Class<?> entityClass) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(entityClass.getSimpleName() + " \n" + "сущность не найдена"));
    }


    @Override
    public void deleteComment(int idAd, int commentId) {
        AdModel adModel = findEntityByIdOrThrow(adRepository, (long) idAd, Ad.class);
        CommentModel commentModel = findEntityByIdOrThrow(commentRepository, (long) commentId, Comment.class);

        if (!commentModel.getAd().getPk().equals(adModel.getPk())) {
            throw new IllegalArgumentException("Комментарий не относится к указанному объявлению");
        }

        commentRepository.delete(commentModel);
    }

    @Override
    public Comment updateCommentAd(int idAd, int idComment, CreateOrUpdateComment updateComment) {
        AdModel ad = findEntityByIdOrThrow(adRepository, (long) idAd, Ad.class);
        CommentModel comment = findEntityByIdOrThrow(commentRepository, (long) idComment, Comment.class);

        if (!comment.getAd().getPk().equals(ad.getPk())) {
            throw new IllegalArgumentException("CКомментарий не относится к указанному объявлению");
        }

        comment.setText(updateComment.getText());
        CommentModel savedComment = commentRepository.save(comment);
        return mapper.toCommentDto(savedComment);
    }
}
