package ru.petsy.jtcw.repositories;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.petsy.jtcw.entities.Animal;


@RunWith(SpringRunner.class)
@SpringBootTest
public class AnimalRepositoryTest {
    @Autowired
    private AnimalRepository animalRepository;

    @Test
    @DisplayName("AnimalRepo saving test")
    public void findAnimalTest(){
        Animal animal = new Animal();
        animal.setName("TEST");
        animal.setRace("TEST");
        animal.setBreed("TEST");
        animal.setAge(0);

        animalRepository.save(animal);

        Assertions.assertEquals("TEST", animalRepository.findByName("TEST").getName());

        animalRepository.delete(animal);
    }

}
