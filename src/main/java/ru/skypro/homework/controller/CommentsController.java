package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.Comments;
import ru.skypro.homework.dto.CreateOrUpdateComment;

/**
 * Контроллер для управления комментариями к объявлениям.
 * Предоставляет REST API для получения, добавления, удаления и обновления комментариев.
 */
@RestController
@Tag(name = "Комментарии")
@RequiredArgsConstructor
public class CommentsController {

    /**
     * Получает все комментарии для указанного объявления.
     *
     * @param id идентификатор объявления, для которого необходимо получить комментарии.
     * @return ResponseEntity с кодом состояния 200 (OK) и списком комментариев.
     */
    @GetMapping("/ads/{id}/comments")
    public ResponseEntity<Comments> getCommendsAd(@PathVariable Integer id) {
        return ResponseEntity.ok().build();
    }

    /**
     * Добавляет новый комментарий к указанному объявлению.
     *
     * @param id идентификатор объявления, к которому добавляется комментарий.
     * @param createOrUpdateComment объект, содержащий данные нового комментария.
     * @return ResponseEntity с кодом состояния 200 (OK) и добавленным комментарием.
     */
    @PostMapping("/ads/{id}/comments")
    public ResponseEntity<Comment> addCommentAd(@PathVariable("id") Integer id,
                                                @RequestBody CreateOrUpdateComment createOrUpdateComment) {
        return ResponseEntity.ok().build();
    }

    /**
     * Удаляет комментарий из указанного объявления.
     *
     * @param adId идентификатор объявления, из которого удаляется комментарий.
     * @param commentId идентификатор удаляемого комментария.
     * @return ResponseEntity с кодом состояния 200 (OK), если удаление прошло успешно.
     */
    @DeleteMapping("/ads/{adId}/comments/{commentId}")
    public ResponseEntity<Comment> deleteCommentAd(@PathVariable("adId") Integer adId,
                                                   @PathVariable("commentId") Integer commentId) {
        return ResponseEntity.ok().build();
    }

    /**
     * Обновляет существующий комментарий к указанному объявлению.
     *
     * @param adId идентификатор объявления, к которому относится обновляемый комментарий.
     * @param commentId идентификатор обновляемого комментария.
     * @param createOrUpdateComment объект, содержащий обновленные данные комментария.
     * @return ResponseEntity с кодом состояния 200 (OK) и обновленным комментарием.
     */
    @PatchMapping("/ads/{adId}/comments/{commentId}")
    public ResponseEntity<Comment> updateCommentAd(@PathVariable("adId") Integer adId,
                                                   @PathVariable("commentId") Integer commentId,
                                                   @RequestBody CreateOrUpdateComment createOrUpdateComment) {
        return ResponseEntity.ok().build();
    }
}