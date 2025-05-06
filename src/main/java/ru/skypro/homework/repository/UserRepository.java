package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.model.UserModel;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {


    boolean existsByEmail(String email);


    Optional<UserModel> findByEmail(String email);

    boolean existsByUsername(String username);

    UserModel findByUsername(String username);
}
