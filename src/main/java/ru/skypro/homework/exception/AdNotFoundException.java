package ru.skypro.homework.exception;

public class AdNotFoundException extends RuntimeException {
  public AdNotFoundException(long id) {
    super("Объявление с ID " + id + " не найдено");
  }
}
