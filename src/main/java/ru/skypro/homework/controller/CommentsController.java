package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.Comments;
import ru.skypro.homework.dto.CreateOrUpdateComment;

@RestController
@Tag(name = "Комментарии")
@RequiredArgsConstructor
public class CommentsController {

    @GetMapping("/ads/{id}/comments")
    public ResponseEntity<Comments> getCommendsAd(@PathVariable Integer id) {
        return ResponseEntity.ok().build();
    }

    @PostMapping("/ads/{id}/comments")
    public ResponseEntity<Comment> addCommentAd(@PathVariable ("id")
                                                     Integer id,
                                                 @RequestBody CreateOrUpdateComment
                                                         CreateOrUpdateComment ) {

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/ads/{adId}/comments/{commentId}")
    public ResponseEntity<Comment> deleteCommentAd(@PathVariable("adId") Integer adId,
                                             @PathVariable("commentId") Integer commentId) {
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/ads/{adId}/comments/{commentId}")
    public ResponseEntity<Comment> updateCommentAd(@PathVariable("adId") Integer adId,
                                                   @PathVariable("commentId") Integer commentId,
                                                   @RequestBody CreateOrUpdateComment
                                                               CreateOrUpdateComment) {
        return ResponseEntity.ok().build();
    }


}
