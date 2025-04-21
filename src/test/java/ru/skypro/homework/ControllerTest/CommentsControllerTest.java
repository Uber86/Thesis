package ru.skypro.homework.ControllerTest;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.skypro.homework.controller.CommentsController;
import ru.skypro.homework.dto.CreateOrUpdateComment;

public class CommentsControllerTest {

    @InjectMocks
    private CommentsController commentsController;

    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(commentsController).build();
    }

    @Test
    public void testGetCommentsAd() throws Exception {
        Integer adId = 1;

        mockMvc.perform(get("/ads/{id}/comments", adId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteCommentAd() throws Exception {
        Integer adId = 1;
        Integer commentId = 1;

        mockMvc.perform(delete("/ads/{adId}/comments/{commentId}", adId, commentId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}