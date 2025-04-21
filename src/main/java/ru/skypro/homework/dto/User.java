package ru.skypro.homework.dto;

import lombok.Data;

import java.util.List;

@Data
public class User {

    private int id;
    private String email;
    private String firstName;
    private String lastName;
    private String phone;
    private List<Role>role;
    private String image;

}
