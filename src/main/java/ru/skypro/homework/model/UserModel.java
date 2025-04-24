package ru.skypro.homework.model;

import ru.skypro.homework.dto.Role;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;


@Entity(name = "UserModel")
@Table(name = "users")
public class UserModel {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email")
    private String email;

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
    //@Enumerated(EnumType.STRING)
    @Column(name = "role")
    private List<Role> role;

    @Column(name = "image")
    private String image;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AdModel> ads;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CommentModel> comments;

    public UserModel() {
    }

    public UserModel(Long id, String email, String username,
                     String password, String firstName,
                     String lastName, String phone,
                     List<Role> role, String image,
                     List<AdModel> ads, List<CommentModel> comments) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.role = role;
        this.image = image;
        this.ads = ads;
        this.comments = comments;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Role> getRole() {
        return role;
    }

    public void setRole(List<Role> role) {
        this.role = role;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<AdModel> getAds() {
        return ads;
    }

    public void setAds(List<AdModel> ads) {
        this.ads = ads;
    }

    public List<CommentModel> getComments() {
        return comments;
    }

    public void setComments(List<CommentModel> comments) {
        this.comments = comments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserModel userModel = (UserModel) o;
        return Objects.equals(id, userModel.id) && Objects.equals(email, userModel.email) && Objects.equals(username, userModel.username) && Objects.equals(password, userModel.password) && Objects.equals(firstName, userModel.firstName) && Objects.equals(lastName, userModel.lastName) && Objects.equals(phone, userModel.phone) && Objects.equals(role, userModel.role) && Objects.equals(image, userModel.image) && Objects.equals(ads, userModel.ads) && Objects.equals(comments, userModel.comments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, username, password, firstName, lastName, phone, role, image, ads, comments);
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phone='" + phone + '\'' +
                ", role=" + role +
                ", image='" + image + '\'' +
                ", ads=" + ads +
                ", comments=" + comments +
                '}';
    }
}
