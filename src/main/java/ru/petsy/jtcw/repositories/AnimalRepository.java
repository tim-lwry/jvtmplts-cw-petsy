package ru.petsy.jtcw.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.petsy.jtcw.entities.AdoptionRequest;
import ru.petsy.jtcw.entities.Animal;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Integer> {
    Animal findByName(String name);

    Animal findById(int id);

    Animal findByRace(String race);

}
