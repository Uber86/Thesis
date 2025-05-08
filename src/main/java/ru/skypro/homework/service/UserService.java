package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;

import java.io.IOException;

/**
 * Интерфейс для работы с пользователями.
 */
public interface UserService {

    /**
     * Обновляет пароль пользователя.
     *
     * @param newPassword объект, содержащий новый пароль и подтверждение пароля.
     * @return true, если пароль успешно обновлен; false в противном случае.
     */
    boolean updatePassword(NewPassword newPassword);

    /**
     * Получает текущего аутентифицированного пользователя.
     *
     * @return объект User, представляющий текущего пользователя.
     */
    User getCurrentUser();

    /**
     * Обновляет информацию о пользователе.
     *
     * @param updateUser объект, содержащий обновленные данные пользователя.
     * @return объект UpdateUser с обновленной информацией о пользователе.
     */
    UpdateUser updateUserInfo(UpdateUser updateUser);

    /**
     * Обновляет изображение профиля пользователя.
     *
     * @param username имя пользователя, для которого нужно обновить изображение.
     * @param imageFile файл изображения для загрузки.
     * @return строка с сообщением о результате операции.
     * @throws IOException если произошла ошибка при обработке файла изображения.
     */
    String updateUserImage(String username, MultipartFile imageFile) throws IOException;

}
