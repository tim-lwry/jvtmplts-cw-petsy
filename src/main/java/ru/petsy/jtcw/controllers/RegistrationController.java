package ru.petsy.jtcw.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.petsy.jtcw.configuration.SiteConfig;
import ru.petsy.jtcw.entities.Adopter;
import ru.petsy.jtcw.repositories.UserRepository;

import javax.validation.Valid;

@Controller
public class RegistrationController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    SiteConfig securityConfig;

    @GetMapping("/registration")
    public String showRegistrationForm(Adopter user, Model model) {
        model.addAttribute("user", new Adopter());
        return "registration";
    }

    @PostMapping("/registration")
    public String completeRegistration(@Valid Adopter user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("user", user);
            return "registration";
        }

        if (!user.getPassword().equals(user.getRepeated())) {
            model.addAttribute("reperr", "Пароли не совпадают");
            return "registration";
        }

        if (userRepository.findByUsername(user.getUsername()) == null) {
            user.setPassword(securityConfig.encoder().encode(user.getPassword()));
            userRepository.save(user);
            model.addAttribute("message", "Регистрация успешна");
            model.addAttribute("onload", "redirectTimer()");
        } else {
            bindingResult.rejectValue("username", "customer.username", "Данный пользователь уже существует");
        }
        return "registration";
    }
}
