package ru.skypro.homework.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity(name = "CommentModel")
@Table(name = "comments")
public class CommentModel {

    @Id
    @Column(name = "pk")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pk;

    @ManyToOne
    @JoinColumn(name = "ad_id")
    private AdModel ad;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserModel author;

    @Column(name = "create_at")
    private LocalDateTime createAt;

    @Column(name = "text")
    private String text;

    public CommentModel() {
    }

    public CommentModel(Long pk, AdModel ad,
                        UserModel author, LocalDateTime createAt,
                        String text) {
        this.pk = pk;
        this.ad = ad;
        this.author = author;
        this.createAt = createAt;
        this.text = text;
    }

    public Long getPk() {
        return pk;
    }

    public void setPk(Long pk) {
        this.pk = pk;
    }

    public AdModel getAd() {
        return ad;
    }

    public void setAd(AdModel ad) {
        this.ad = ad;
    }

    public UserModel getAuthor() {
        return author;
    }

    public void setAuthor(UserModel author) {
        this.author = author;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommentModel that = (CommentModel) o;
        return Objects.equals(pk, that.pk) && Objects.equals(ad, that.ad) && Objects.equals(author, that.author) && Objects.equals(createAt, that.createAt) && Objects.equals(text, that.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pk, ad, author, createAt, text);
    }

    @Override
    public String toString() {
        return "CommentModel{" +
                "pk=" + pk +
                ", ad=" + ad +
                ", author=" + author +
                ", createAt=" + createAt +
                ", text='" + text + '\'' +
                '}';
    }
}
