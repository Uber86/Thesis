package ru.skypro.homework.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.mapper.CommentMapper;
import ru.skypro.homework.model.AdModel;
import ru.skypro.homework.model.CommentModel;
import ru.skypro.homework.model.UserModel;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CommentServiceImplTest {

    @Mock
    private CommentRepository commentRepository;

    @Mock
    private CommentMapper commentMapper;

    @Mock
    private AdRepository adRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private CommentServiceImpl commentService;

    private AdModel adModel;
    private UserModel userModel;
    private CommentModel commentModel;
    private Comment commentDto;
    private CreateOrUpdateComment createOrUpdateComment;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        adModel = new AdModel();
        adModel.setPk(1L);

        userModel = new UserModel();
        userModel.setUsername("user");

        commentModel = new CommentModel();
        commentModel.setPk(1l);
        commentModel.setAd(adModel);
        commentModel.setAuthor(userModel);
        commentModel.setCreateAt(LocalDateTime.now());
        commentModel.setText("text");

        commentDto = new Comment();
        commentDto.setPk(1);
        commentDto.setText("text");

        createOrUpdateComment = new CreateOrUpdateComment();
        createOrUpdateComment.setText("text");
    }

    @Test
    void createNewCommentSuccess() {
        when(adRepository.findById(1l)).thenReturn(Optional.of(adModel));
        when(userRepository.findAll()).thenReturn(List.of(userModel));
        when(commentMapper.toCommentModel(createOrUpdateComment)).thenReturn(commentModel);
        when(commentRepository.save(commentModel)).thenReturn(commentModel);
        when(commentMapper.toCommentDto(commentModel)).thenReturn(commentDto);

        Comment result = commentService.createNewComment(1, createOrUpdateComment);

        assertNotNull(result);
        assertEquals("text", result.getText());
        verify(adRepository).findById(1l);
        verify(userRepository).findAll();
        verify(commentMapper).toCommentModel(createOrUpdateComment);
        verify(commentRepository).save(commentModel);
        verify(commentMapper).toCommentDto(commentModel);
    }
}