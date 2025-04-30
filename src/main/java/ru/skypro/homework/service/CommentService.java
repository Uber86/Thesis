package ru.skypro.homework.service;

import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.Comments;
import ru.skypro.homework.dto.CreateOrUpdateComment;

public interface CommentService {

    Comments getAllCommentsByAdId(int idAd);

    Comment createNewComment(int idAd, CreateOrUpdateComment createComment);

    void deleteComment(int idAd, int commentId);

    Comment updateCommentAd(int idAd, int idComment, CreateOrUpdateComment updateComment);


}
