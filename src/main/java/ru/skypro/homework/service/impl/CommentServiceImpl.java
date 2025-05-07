package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.Comments;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.exception.AdNotFoundException;
import ru.skypro.homework.exception.CommentNotFoundException;
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
import java.util.Optional;
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

    private Long getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            String username = authentication.getName();
            UserDetails userDetails = usersDetailsService.loadUserByUsername(username);
            UserModel user = userRepository.findByUsername(userDetails.getUsername());
            if (user == null){
                throw new UserNotFoundException("User not found");
            }
            return user.getId();
        }
        throw new UserNotFoundException("No authenticated user found");
    }


    @Override
    public Comments getAllCommentsByAdId(int idAd) {
        Optional<AdModel> adModelOptional = adRepository
                .findById((long) idAd);
        AdModel adModel = adModelOptional.orElse(null);
        List<CommentModel> commentModel = adModel != null ? adModel.getComments() : null;
        List<Comment> toCommentDtoList = mapper.toCommentDtoList(commentModel);
        Comments comments = new Comments();
        comments.setComments(toCommentDtoList);
        comments.setCount(toCommentDtoList.size());
        return comments;

    }

    @Override
    @Transactional
    public Comment createNewComment(int idAd, CreateOrUpdateComment createComment) {
        Optional<AdModel> adModelOptional = adRepository
                .findById((long) idAd);
        AdModel adModel = adModelOptional.orElseThrow(()-> new AdNotFoundException(idAd));
        UserModel userModel = userRepository.findById(getCurrentUserId())
                .orElseThrow(()-> new UserNotFoundException("User not init"));
        CommentModel commentModel = createComment != null ? mapper.toCommentModel(createComment): null;
        commentModel.setAuthor(userModel);
        commentModel.setAd(adModel);
        commentModel.setCreateAt(LocalDateTime.now());
        adModel.getComments().add(commentModel);
        adRepository.save(adModel);
        return mapper.toCommentDto(commentModel);
    }

//    private <T> T findEntityByIdOrThrow(JpaRepository<T, Long> repository, Long id, Class<?> entityClass) {
//        return repository.findById(id)
//                .orElseThrow(() -> new EntityNotFoundException(entityClass.getSimpleName() + " \n" + "сущность не найдена"));
//    }


    @Override
    @Transactional
    public void deleteComment(int idAd, int commentId) {
        Optional<AdModel> adModelOptional = adRepository
                .findById((long) idAd);
        AdModel adModel = adModelOptional.orElseThrow(() -> new AdNotFoundException(idAd));
        UserModel userModel = userRepository.findById(getCurrentUserId())
                .orElseThrow(()-> new UserNotFoundException("User not init"));
        Optional<CommentModel> commentModelOptional = commentRepository.findById((long) commentId);
        CommentModel commentModel = commentModelOptional
                .orElseThrow(()-> new CommentNotFoundException(commentId));
        adModel.getComments().remove(commentModel);
        commentRepository.delete(commentModel);
//        AdModel adModel = findEntityByIdOrThrow(adRepository, (long) idAd, Ad.class);
//        List<AdModel> adModel = adRepository.findByPk(idAd);
//        UserModel author = userRepository.findByUsername(getCurrentUsername());
//        CommentModel commentModel = commentRepository.
//                findById((long)commentId).
//                filter(it->it.equals(commentId)).orElseThrow();
//        if (commentModel.getAuthor().equals(author)) {
//            commentRepository.delete(commentModel);
//        }
//////        CommentModel commentModel = findEntityByIdOrThrow(commentRepository, (long) commentId, Comment.class);
////        if (!commentModel.getAd().getPk().equals(adModel.getPk())) {
////            throw new IllegalArgumentException("Комментарий не относится к указанному объявлению");
////        }
    }

    @Override
    public Comment updateCommentAd(int idAd, int idComment, CreateOrUpdateComment updateComment) {
//        AdModel ad = findEntityByIdOrThrow(adRepository, (long) idAd, Ad.class);
//        CommentModel comment = findEntityByIdOrThrow(commentRepository, (long) idComment, Comment.class);
        AdModel adModel = (AdModel) commentRepository.findByAd(idAd);
        CommentModel commentModel = commentRepository.
                findById((long)idComment).
                filter(it->it.equals(idComment)).orElseThrow();
        UserModel author = userRepository.findByUsername(getCurrentUsername());
        if (adModel.getAuthor().equals(author) && commentModel.getAuthor().equals(author)) {
            CommentModel savedComment = commentRepository.save(mapper.toCommentModel(updateComment));
            return mapper.toCommentDto(savedComment);
        }

//        if (!comment.getAd().getPk().equals(ad.getPk())) {
//            throw new IllegalArgumentException("CКомментарий не относится к указанному объявлению");
//        }
        throw new UserNotFoundException("No authenticated user found");
    }
}
