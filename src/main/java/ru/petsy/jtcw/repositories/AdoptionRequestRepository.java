package ru.petsy.jtcw.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.petsy.jtcw.entities.Adopter;
import ru.petsy.jtcw.entities.AdoptionRequest;
import ru.petsy.jtcw.entities.Animal;

public interface AdoptionRequestRepository extends JpaRepository<AdoptionRequest, Integer> {
    AdoptionRequest findByAdopter(String adopter);

    AdoptionRequest findByAnimal(Animal animal);

}
