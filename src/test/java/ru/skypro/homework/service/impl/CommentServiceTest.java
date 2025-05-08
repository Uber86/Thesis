package ru.skypro.homework.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.Comments;
import ru.skypro.homework.dto.CreateOrUpdateComment;
import ru.skypro.homework.exception.AdNotFoundException;
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

public class CommentServiceTest {

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
        when(adRepository.findById(1L)).thenReturn(Optional.of(adModel));
        when(userRepository.findAll()).thenReturn(List.of(userModel));
        when(commentMapper.toCommentModel(createOrUpdateComment)).thenReturn(commentModel);
        when(commentRepository.save(commentModel)).thenReturn(commentModel);
        when(commentMapper.toCommentDto(commentModel)).thenReturn(commentDto);

        Comment result = commentService.createNewComment(1, createOrUpdateComment);

        assertNotNull(result);
        assertEquals("text", result.getText());
        verify(adRepository).findById(1L);
        verify(userRepository).findAll();
        verify(commentMapper).toCommentModel(createOrUpdateComment);
        verify(commentRepository).save(commentModel);
        verify(commentMapper).toCommentDto(commentModel);
    }
    @Test
    void createNewComment_WhenAdNotFound_ThrowsAdNotFoundException() {
        // Arrange
        when(adRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(AdNotFoundException.class, () -> {
            commentService.createNewComment(1, createOrUpdateComment);
        });

        verify(adRepository).findById(1L);
        verifyNoInteractions(userRepository, commentMapper, commentRepository);
    }
    @Test
    void getAllCommentsByAdId_WhenAdExists_ReturnsComments() {
        // Arrange
        when(adRepository.findById(1L)).thenReturn(Optional.of(adModel));
        when(commentMapper.toCommentDtoList(adModel.getComments())).thenReturn(List.of(commentDto));

        // Act
        Comments result = commentService.getAllCommentsByAdId(1);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getCount());
        assertEquals("text", result.getComments().get(0).getText());

        verify(adRepository).findById(1L);
        verify(commentMapper).toCommentDtoList(adModel.getComments());
    }
}