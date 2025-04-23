package ru.skypro.homework.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.persistence.*;

@Entity
@Table(name = "ads")
@Data
public class AdModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long pk;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "user_id")
    private UserModel author;

    @Lob
    @Column(name = "image")
    private String image;

    @Column(name = "price")

    private int price;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

}
