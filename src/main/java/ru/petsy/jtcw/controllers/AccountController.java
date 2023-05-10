package ru.petsy.jtcw.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.petsy.jtcw.entities.Adopter;
import ru.petsy.jtcw.entities.AdoptionRequest;
import ru.petsy.jtcw.entities.Animal;
import ru.petsy.jtcw.repositories.AdoptionRequestRepository;
import ru.petsy.jtcw.repositories.AnimalRepository;
import ru.petsy.jtcw.repositories.UserRepository;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class AccountController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    AdoptionRequestRepository adoptionRequestRepository;

    @Autowired
    AnimalRepository animalRepository;

    @GetMapping("/account")
    public String showAccountPage(HttpServletRequest request, Model model) {
        model.addAttribute("username", request.getUserPrincipal().getName());
        model.addAttribute("linkOutOrUp", "/logout");
        model.addAttribute("textOutOrUp", "Выйти");
        model.addAttribute("linkInOrAccount", "/account");
        model.addAttribute("textInOrAccount", "Аккаунт");
        model.addAttribute("welcome", "Вы вошли как " + request.getUserPrincipal().getName() + "!");

        Adopter adopter = userRepository.findByUsername(request.getUserPrincipal().getName());


        model.addAttribute("requests", adopter.getAdoptionRequests());

        return "account";
    }

    @GetMapping("/account/{id}")
    public String deleteOrder(@PathVariable int id, HttpServletRequest request, Model model) {
        model.addAttribute("username", request.getUserPrincipal().getName());
        model.addAttribute("linkOutOrUp", "/logout");
        model.addAttribute("textOutOrUp", "Выйти");
        model.addAttribute("linkInOrAccount", "/account");
        model.addAttribute("textInOrAccount", "Аккаунт");
        model.addAttribute("welcome", "Вы вошли как " + request.getUserPrincipal().getName() + "!");

        Adopter adopter = userRepository.findByUsername(request.getUserPrincipal().getName());
        if (adoptionRequestRepository.findById(id).isPresent()) {
            adopter.adoptionRequests.remove(adoptionRequestRepository.getById(id));
            adoptionRequestRepository.deleteById(id);
            System.out.println("Found");
        }
        userRepository.save(adopter);

        model.addAttribute("requests", adopter.getAdoptionRequests());

        return "account";
    }
}
