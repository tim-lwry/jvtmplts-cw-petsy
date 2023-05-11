package ru.petsy.jtcw.repositories;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.petsy.jtcw.entities.Adopter;
import ru.petsy.jtcw.services.AdopterService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("UserRepository saving test")
    public void findByUsernameTest1(){
        Adopter adopter = new Adopter();
        adopter.setUsername("TESTUSER");
        adopter.setPassword("TESTPASS");
        adopter.setRepeated("TESTPASS");

        userRepository.save(adopter);

        Assertions.assertEquals("TESTUSER", userRepository.findByUsername("TESTUSER").getUsername());
    }

    @Test
    @DisplayName("UserRepository update test")
    public void findByUsernameTest2(){
        Adopter adopter = new Adopter();

        adopter.setUsername("TESTUSER");
        adopter.setPassword("pass1");
        adopter.setRepeated("pass1");

        userRepository.save(adopter);

        adopter.setPassword("pass2");
        adopter.setRepeated("pass2");

        userRepository.save(adopter);

        Assertions.assertEquals("pass2", userRepository.findByUsername("TESTUSER").getPassword());

        userRepository.delete(adopter);
    }
}
