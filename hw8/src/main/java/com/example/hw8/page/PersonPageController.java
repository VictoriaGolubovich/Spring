package com.example.hw8.page;

import com.example.hw8.service.PersonPageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Controller
@RequestMapping("/home/persons")
public class PersonPageController {
    private final PersonPageService service;

    public PersonPageController(PersonPageService service) {
        this.service = service;
    }

    @GetMapping
    // @Secured({"admin", "user"})
    public String getAllPersons(Model model) {
        List<PersonPageDto> persons = service.findAll();
        model.addAttribute("persons", persons);
        return "persons.html";
    }

    // GET /home/persons/{id}
    @GetMapping("/{id}")
    public String getPersonProfile(@PathVariable Long id, Model model) {
        Optional<PersonPageDto> personOpt = service.findById(id);
        if (personOpt.isEmpty()) {
            throw new NoSuchElementException();
        }

        model.addAttribute("person", personOpt.get());
        return "personProfile.html";
    }

}