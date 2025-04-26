package ru.skypro.homework.model;


import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "ads")
public class AdModel {

    @Id
    @Column(name = "pk")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pk;

    @ManyToOne
    @JoinColumn(name= "user_id")
    private UserModel author;

    @Column(name = "image")
    private String image;

    @Column(name = "price")
    private int price;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "ad", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CommentModel> comments;

    public AdModel() {
    }

    public AdModel(Long pk, UserModel author,
                   String image, int price,
                   String title, String description,
                   List<CommentModel> comments) {
        this.pk = pk;
        this.author = author;
        this.image = image;
        this.price = price;
        this.title = title;
        this.description = description;
        this.comments = comments;
    }

    public Long getPk() {
        return pk;
    }

    public void setPk(Long pk) {
        this.pk = pk;
    }

    public UserModel getAuthor() {
        return author;
    }

    public void setAuthor(UserModel author) {
        this.author = author;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String imageAd) {
        this.image = imageAd;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        AdModel adModel = (AdModel) o;
        return price == adModel.price && Objects.equals
                (pk, adModel.pk) && Objects.equals(author, adModel.author)
                && Objects.equals(image, adModel.image)
                && Objects.equals(title, adModel.title)
                && Objects.equals(description, adModel.description)
                && Objects.equals(comments, adModel.comments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pk, author,
                image, price, title,
                description, comments);
    }

    @Override
    public String toString() {
        return "AdModel{" +
                "pk=" + pk +
                ", author=" + author +
                ", imageAd='" + image + '\'' +
                ", price=" + price +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", comments=" + comments +
                '}';
    }
}
