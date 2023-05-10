package ru.petsy.jtcw.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.petsy.jtcw.entities.Adopter;
import ru.petsy.jtcw.entities.AdoptionRequest;
import ru.petsy.jtcw.entities.Animal;
import ru.petsy.jtcw.repositories.AdoptionRequestRepository;
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

    @Autowired
    AdoptionRequestRepository adoptionRequestRepository;

    @GetMapping("/request/{id}")
    public String makeRequest(@PathVariable int id, HttpServletRequest request, Model model, RedirectAttributes redirectAttributes) {
        model.addAttribute("username", request.getUserPrincipal().getName());
        model.addAttribute("linkOutOrUp", "/logout");
        model.addAttribute("textOutOrUp", "Выйти");
        model.addAttribute("linkInOrAccount", "/account");
        model.addAttribute("textInOrAccount", "Аккаунт");

        Animal animal = animalRepository.findById(id);
        Adopter adopter = userRepository.findByUsername(request.getUserPrincipal().getName());
        model.addAttribute("animal", animal);

        if(adoptionRequestRepository.findByAnimal(animal) == null) {

            AdoptionRequest adoptionRequest = new AdoptionRequest();
            adoptionRequest.setAnimal(animal);
            adoptionRequest.setAdopter(adopter);
            adoptionRequestRepository.save(adoptionRequest);

            redirectAttributes.addFlashAttribute("success", "Заявка успешно добавлена!😺");
        }
        else{
            redirectAttributes.addFlashAttribute("success", "Заявка уже отправлена!😽");
        }
        //model.addAttribute();

        return "redirect:/animal/" + String.valueOf(id);
    }
}
