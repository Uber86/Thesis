package ru.skypro.homework.dto;

import lombok.Data;

import java.util.List;

@Data
public class User {

    private Integer id;
    private String email;
    private String firstName;
    private String lastName;
    private String phone;
    private List<Role> role;
    private String image;
}
