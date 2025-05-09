package ru.skypro.homework.service;

import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.Comments;
import ru.skypro.homework.dto.CreateOrUpdateComment;

/**
 * Интерфейс для работы с комментариями.
 */
public interface CommentService {

    /**
     * Получает все комментарии по идентификатору объявления.
     *
     * @param idAd идентификатор объявления, для которого нужно получить комментарии.
     * @return объект Comments, содержащий список всех комментариев для указанного объявления.
     */
    Comments getAllCommentsByAdId(int idAd);

    /**
     * Создает новый комментарий для указанного объявления.
     *
     * @param idAd идентификатор объявления, к которому добавляется комментарий.
     * @param createComment объект, содержащий данные нового комментария.
     * @return созданный объект Comment.
     */
    Comment createNewComment(int idAd, CreateOrUpdateComment createComment);

    /**
     * Удаляет комментарий по его идентификатору и идентификатору объявления.
     *
     * @param idAd идентификатор объявления, к которому относится комментарий.
     * @param commentId идентификатор комментария для удаления.
     */
    void deleteComment(int idAd, int commentId);

    /**
     * Обновляет существующий комментарий для указанного объявления.
     *
     * @param idAd идентификатор объявления, к которому относится обновляемый комментарий.
     * @param idComment идентификатор комментария для обновления.
     * @param updateComment объект, содержащий обновленные данные комментария.
     * @return обновленный объект Comment.
     */
    Comment updateCommentAd(int idAd, int idComment, CreateOrUpdateComment updateComment);
}
