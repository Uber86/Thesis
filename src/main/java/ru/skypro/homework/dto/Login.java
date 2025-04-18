package ru.skypro.homework.dto;

import lombok.Data;

import javax.persistence.Embedded;


@Data
public class Login {
    private String username;
    private String password;
}
