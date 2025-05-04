package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.model.AdModel;
import ru.skypro.homework.model.UserModel;

import java.util.List;
import java.util.Optional;

@Repository
public interface AdRepository extends JpaRepository<AdModel, Long> {

    List<AdModel> findByPk(long idpk);

    Optional<Ads> findByUser(User user);

    List<Ad> findAllByUser(UserModel author);

    List<AdModel> findAllByAuthor(UserModel user);
}
