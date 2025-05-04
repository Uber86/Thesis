package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.model.UserModel;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {

    Optional<UserModel> findById(Long id);

    boolean existsByEmail(String email);

    UserModel findByUsername(String username);

    List<UserModel> findAll();

    Optional<UserModel> findByEmail(String email);

    boolean existsByUsername(String username);
}
