package ru.skypro.homework.exception;

public class CommentNotFoundException extends RuntimeException{
    public CommentNotFoundException (long id) {
        super("Комментарии с ID " + id + " отсутствует");
    }
}
