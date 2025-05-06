package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.model.AdModel;
import ru.skypro.homework.model.UserModel;

import java.util.List;

@Repository
public interface AdRepository extends JpaRepository<AdModel, Long> {

    List<AdModel> findAllByAuthor(UserModel user);

    List<AdModel> findByPk(int userId);

    AdModel findByPkAd(int pk);
}
