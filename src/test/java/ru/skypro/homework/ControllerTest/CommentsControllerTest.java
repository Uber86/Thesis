package ru.skypro.homework.ControllerTest;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.skypro.homework.controller.CommentsController;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.service.CommentService;

@ExtendWith(MockitoExtension.class)
public class CommentsControllerTest {

    @Mock
    private CommentService commentService;

    @InjectMocks
    private CommentsController commentsController;

    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(commentsController).build();
    }

    @Test
    public void testGetCommentsAd() throws Exception {
        int adId = 1;
        Comments expectedComments = new Comments();

        when(commentService.getAllCommentsByAdId(adId)).thenReturn(expectedComments);

        mockMvc.perform(get("/ads/{id}/comments", adId))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(expectedComments)));

        verify(commentService, times(1)).getAllCommentsByAdId(adId);
    }

    @Test
    public void testAddCommentAd() throws Exception {
        int adId = 1;
        CreateOrUpdateComment newComment = new CreateOrUpdateComment();
        newComment.setText("Test comment"); // Устанавливаем текст через setter
        Comment expectedComment = new Comment();

        when(commentService.createNewComment(adId, newComment)).thenReturn(expectedComment);

        mockMvc.perform(post("/ads/{id}/comments", adId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newComment)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(expectedComment)));

        verify(commentService, times(1)).createNewComment(adId, newComment);
    }

    @Test
    public void testDeleteCommentAd() throws Exception {
        int adId = 1;
        int commentId = 1;

        mockMvc.perform(delete("/ads/{adId}/comments/{commentId}", adId, commentId))
                .andExpect(status().isOk());

        verify(commentService, times(1)).deleteComment(adId, commentId);
    }

    @Test
    public void testUpdateCommentAd() throws Exception {
        int adId = 1;
        int commentId = 1;
        CreateOrUpdateComment updatedComment = new CreateOrUpdateComment();
        updatedComment.setText("Updated text"); // Устанавливаем текст через setter
        Comment expectedComment = new Comment();

        when(commentService.updateCommentAd(adId, commentId, updatedComment)).thenReturn(expectedComment);

        mockMvc.perform(patch("/ads/{adId}/comments/{commentId}", adId, commentId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedComment)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(expectedComment)));

        verify(commentService, times(1)).updateCommentAd(adId, commentId, updatedComment);
    }
}