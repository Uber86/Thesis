package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.homework.model.AdModel;

@Repository
public interface AdRepository extends JpaRepository<AdModel, Long> {
}
