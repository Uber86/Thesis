package ru.skypro.homework.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "avatars")
@Data
public class Avatar {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @Column(name = "media_type")
    private String mediaType;
    @Lob
    @Column(name = "data")
    private byte[] data;

    @OneToOne
    @JoinColumn(name = " user_id")
    private User user;

}
