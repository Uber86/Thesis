package ru.skypro.homework.dto;

import lombok.Data;

import javax.persistence.Embedded;


@Data
public class Login {

    @Embedded
    private String username;
    @Embedded
    private String password;
}
