package ru.petsy.jtcw.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.petsy.jtcw.entities.Adopter;
import ru.petsy.jtcw.entities.Animal;
import ru.petsy.jtcw.repositories.AdoptionRequestRepository;
import ru.petsy.jtcw.repositories.AnimalRepository;
import ru.petsy.jtcw.repositories.UserRepository;

import javax.servlet.http.HttpServletRequest;
@Controller
public class AnimalsController {

    @Autowired
    AnimalRepository animalRepository;

    @GetMapping("/animals")
    public String showAnimalsPage(HttpServletRequest request, Model model) {
        model.addAttribute("username", request.getUserPrincipal().getName());
        model.addAttribute("linkOutOrUp", "/logout");
        model.addAttribute("textOutOrUp", "Выйти");
        model.addAttribute("linkInOrAccount", "/account");
        model.addAttribute("textInOrAccount", "Аккаунт");
        model.addAttribute("welcome", "Вы вошли как " + request.getUserPrincipal().getName() + "!");


        model.addAttribute("animals", animalRepository.findAll());

        return "animals";
    }

    @GetMapping("/animal/{id}")
    public String deleteOrder(@PathVariable int id, HttpServletRequest request, Model model) {
        model.addAttribute("username", request.getUserPrincipal().getName());
        model.addAttribute("linkOutOrUp", "/logout");
        model.addAttribute("textOutOrUp", "Выйти");
        model.addAttribute("linkInOrAccount", "/account");
        model.addAttribute("textInOrAccount", "Аккаунт");
        model.addAttribute("welcome", "Вы вошли как " + request.getUserPrincipal().getName() + "!");


        Animal animal = animalRepository.findById(id);

        model.addAttribute("animal", animal);

        return "animal";
    }
}

