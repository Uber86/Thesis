package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.Comments;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.service.CommentService;
import ru.skypro.homework.service.UserService;

/**
 * Контроллер для управления комментариями к объявлениям.
 * Предоставляет REST API для получения, добавления, удаления и обновления комментариев.
 */
@RestController
@CrossOrigin(value = "http://localhost:3000")
@Tag(name = "Комментарии")
@RequiredArgsConstructor
public class CommentsController {

    public CommentService service;

    /**
     * Получает все комментарии для указанного объявления.
     *
     * @param id идентификатор объявления, для которого необходимо получить комментарии.
     * @return список комментариев.
     */
    @GetMapping("/ads/{id}/comments")
    public ResponseEntity<Comments> getCommendsAd(@PathVariable int id) {
        Comments comments = service.getAllCommentsByAdId(id);
        return ResponseEntity.ok(comments);
    }

    /**
     * Добавляет новый комментарий к указанному объявлению.
     *
     * @param id идентификатор объявления, к которому добавляется комментарий.
     * @param createOrUpdateComment объект, содержащий данные нового комментария.
     * @return добавлены комментарии.
     */
    @PostMapping("/ads/{id}/comments")
    public ResponseEntity<Comment> addCommentAd(@PathVariable("id") int id,
                                                @RequestBody CreateOrUpdateComment
                                                        createOrUpdateComment) {
        Comment comment = service.createNewComment(id, createOrUpdateComment);
        return ResponseEntity.ok(comment);
    }

    /**
     * Удаляет комментарий из указанного объявления.
     *
     * @param adId идентификатор объявления, из которого удаляется комментарий.
     * @param commentId идентификатор удаляемого комментария.
     */
    @DeleteMapping("/ads/{adId}/comments/{commentId}")
    @PreAuthorize("hasRole('ADMIN')")
    public  ResponseEntity<Comment>  deleteCommentAd(@PathVariable("adId") int adId,
                                                   @PathVariable("commentId") int commentId) {
        service.deleteComment(adId, commentId);
        return ResponseEntity.ok().build();
    }

    /**
     * Обновляет существующий комментарий к указанному объявлению.
     *
     * @param adId идентификатор объявления, к которому относится обновляемый комментарий.
     * @param commentId идентификатор обновляемого комментария.
     * @param createOrUpdateComment объект, содержащий обновленные данные комментария.
     * @return обновленное комментарии.
     */
    @PatchMapping("/ads/{adId}/comments/{commentId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Comment> updateCommentAd(@PathVariable("adId") int adId,
                                                   @PathVariable("commentId") int commentId,
                                                   @RequestBody CreateOrUpdateComment
                                                              createOrUpdateComment) {
        Comment comment = service.updateCommentAd(adId, commentId, createOrUpdateComment);
        return ResponseEntity.ok(comment);
    }
}