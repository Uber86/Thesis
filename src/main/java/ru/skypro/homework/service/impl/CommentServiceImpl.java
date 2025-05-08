package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static ru.skypro.homework.dto.Role.ADMIN;


@RequiredArgsConstructor
@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    private final CommentMapper mapper;

    private final AdRepository adRepository;

    private final UserRepository userRepository;

    private final UsersDetailsService usersDetailsService;


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
    @Transactional
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
        AdModel adModel = adRepository.findById((long) idAd)
                .orElseThrow(()-> new AdNotFoundException(idAd));
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
    @Override
    @Transactional
    public void deleteComment(int idAd, int commentId) {
        AdModel adModel = adRepository
                .findById((long) idAd).orElseThrow(() -> new AdNotFoundException(idAd));
        UserModel userModel = userRepository.findById(getCurrentUserId())
                .orElseThrow(()-> new UserNotFoundException("User not init"));
        CommentModel commentModel = commentRepository.findById((long) commentId)
                .orElseThrow(()-> new CommentNotFoundException(commentId));
        if (commentModel.getAuthor().getId().equals(userModel.getId()) || userModel.getRole() == ADMIN) {
            adModel.getComments().remove(commentModel);
            commentRepository.delete(commentModel);
        }
    }

    @Override
    @Transactional
    public Comment updateCommentAd(int idAd, int idComment, CreateOrUpdateComment updateComment) {
        AdModel adModel = adRepository.findById((long) idAd)
                .orElseThrow(() -> new AdNotFoundException(idAd));
        UserModel userModel = userRepository.findById(getCurrentUserId())
                .orElseThrow(()-> new UserNotFoundException("User not found"));
        CommentModel commentModel = commentRepository.findById((long) idComment)
                .orElseThrow(() -> new CommentNotFoundException(idComment));
        if ((userModel.getId()
                .equals(commentModel
                        .getAuthor()
                        .getId()) && adModel.getPk()
                .equals(commentModel
                        .getAd().getPk())) || userModel
                .getRole() == ADMIN) {
            commentModel.setText(updateComment.getText());
            commentModel.setCreateAt(LocalDateTime.now());
            commentRepository.save(commentModel);
            return mapper.toCommentDto(commentModel);

        }
        throw new RuntimeException("Comment user update");
    }
}
