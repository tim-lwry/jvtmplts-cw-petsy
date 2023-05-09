package ru.petsy.jtcw.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.petsy.jtcw.entities.Adopter;
import ru.petsy.jtcw.entities.AdoptionRequest;
import ru.petsy.jtcw.entities.Animal;
import ru.petsy.jtcw.repositories.AnimalRepository;
import ru.petsy.jtcw.repositories.UserRepository;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Date;

@Controller
public class ARequestController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    AnimalRepository animalRepository;

    @GetMapping("/request")
    public String showOrderPage(HttpServletRequest request, Model model) {
        model.addAttribute("adoption_request", new AdoptionRequest());

        model.addAttribute("username", request.getUserPrincipal().getName());
        model.addAttribute("linkOutOrUp", "/logout");
        model.addAttribute("textOutOrUp", "LogOut");
        model.addAttribute("linkInOrAccount", "/account");
        model.addAttribute("textInOrAccount", "Account");

        model.addAttribute("confirm", "false");
        model.addAttribute("another", "true");

        return "request";
    }

    @PostMapping("/request")
    public String completeOrder(@Valid AdoptionRequest adoptionRequest, BindingResult bindingResult, Model model, HttpServletRequest request) {
        model.addAttribute("username", request.getUserPrincipal().getName());
        model.addAttribute("linkOutOrUp", "/logout");
        model.addAttribute("textOutOrUp", "LogOut");
        model.addAttribute("linkInOrAccount", "/account");
        model.addAttribute("textInOrAccount", "Account");

        if (bindingResult.hasErrors()) {
            model.addAttribute("confirm", "false");
            model.addAttribute("another", "true");
            model.addAttribute(adoptionRequest);
            return "request";
        }

        Adopter user = userRepository.findByUsername(request.getUserPrincipal().getName());
        Animal animal = animalRepository.findByName(adoptionRequest.getAnimal().getName());

        adoptionRequest.setAdopter(user);
        adoptionRequest.setAnimal(animal);

        user.adoptionRequests.add(adoptionRequest);
        userRepository.save(user);

        animal.adoptionRequests.add(adoptionRequest);
        animalRepository.save(animal);

        model.addAttribute("confirm", "true");
        model.addAttribute("another", "false");
        model.addAttribute("message", "Заявка была подана");
        return "request";
    }
}
