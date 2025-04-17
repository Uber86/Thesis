package ru.skypro.homework.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import ru.skypro.homework.dto.Role;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name ="username")
    private String username;
    @Column(name ="password")
    private String password;
    @Column(name ="first_name")
    private String firstName;
    @Column(name ="last_name")
    private String lastName;
    @Column(name ="phone")
    private String phone;

    /**
     *Аннотация @ElementCollection хранение базовых или встраиваемых типов в отдельной таблице для Role.class
     * параметр fetch для жадной подгрузки данных
     * Аннотация @CollectionTable указывает что указанное поле будет храниться в отдельной таблице,
     * параметр joinColumns указывает на какое поле будет подвязана роль в данном случаи user_id
     * Аннотация @Enumerated(EnumType.STRING) указываем, что enum будем хранить в виде строки
     */
    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> role;
}
