package ru.petsy.jtcw.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.petsy.jtcw.entities.Adopter;

@Repository
public interface UserRepository extends JpaRepository<Adopter, Integer> {
    Adopter findByUsername(String username);
}
