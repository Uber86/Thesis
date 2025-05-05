package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
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
        if (authentication == null && !authentication.isAuthenticated()) {
            throw new RuntimeException("User is not authenticated");
        }
        Object principal = authentication.getPrincipal();
        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername();
        } else{
            return principal.toString();
        }
    }


    @Override
    public Comments getAllCommentsByAdId(int idAd) {
        List<CommentModel> comment = commentRepository.findAll().stream()
                .filter(id->id.getAd().equals((long)idAd))
                .collect(Collectors.toList());
        List<Comment> toCommentDtoList = mapper.toCommentDtoList(comment);
        Comments comments = new Comments();
        comments.setCount(toCommentDtoList.size());
        comments.setComments(toCommentDtoList);
        return comments;
    }

    @Override
    public Comment createNewComment(int idAd, CreateOrUpdateComment createComment){
        AdModel adModel = adRepository.findById((long)idAd)
                .orElseThrow(()-> new EntityNotFoundException("Ad not found"));
        String username = getCurrentUsername();
        UserModel author = userRepository.findByUsername(username);
        if (author == null) {
            throw new EntityNotFoundException("User not found");
        }
        CommentModel commentModel = mapper.toCommentModel(createComment);
        commentModel.setCreateAt(LocalDateTime.now());
        commentModel.setAd(adModel);
        commentModel.setAuthor(author);
        CommentModel savedComment = commentRepository.save(commentModel);
        return mapper.toCommentDto(savedComment);

    }

    @Override
    public void deleteComment(int idAd, int commentId) {
        AdModel adModel = adRepository.findById((long)idAd)
                .orElseThrow(()-> new EntityNotFoundException("Ad not found"));
        CommentModel commentModel = commentRepository.findById((long) commentId)
                .orElseThrow(()-> new EntityNotFoundException("Comment not found"));
        if (!commentModel.getAd().getPk().equals(adModel.getPk())) {
            throw new IllegalArgumentException();
        }
        commentRepository.delete(commentModel);
    }

    @Override
    public Comment updateCommentAd(int idAd, int idComment, CreateOrUpdateComment updateComment) {
        AdModel ad = adRepository.findById((long)idAd)
                .orElseThrow(()-> new EntityNotFoundException("Ad not found"));
        CommentModel comment = commentRepository.findById((long)idComment)
                .orElseThrow(() -> new EntityNotFoundException("Comment not found"));
        if (!comment.getAd().getPk().equals(ad.getPk())) {
            throw new IllegalArgumentException();
        }
        comment.setText(updateComment.getText());
        CommentModel save = commentRepository.save(comment);
        return mapper.toCommentDto(save);
    }
}
