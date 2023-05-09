package ru.petsy.jtcw.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.petsy.jtcw.entities.Adopter;
import ru.petsy.jtcw.repositories.UserRepository;

@Service
public class AdopterService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Adopter adopter = userRepository.findByUsername(username);

        if (adopter == null)
            throw new UsernameNotFoundException("Пользователь не найден");

        return adopter;
    }
}
