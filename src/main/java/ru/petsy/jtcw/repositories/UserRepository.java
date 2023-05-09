package ru.petsy.jtcw.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.petsy.jtcw.entities.Adopter;

public interface UserRepository extends JpaRepository<Adopter, Integer> {
    Adopter findByUsername(String username);
}
