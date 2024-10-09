package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.service.PersonPageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


@Controller
@RequestMapping("/persons")
@RequiredArgsConstructor
public class PersonPageController {

    private final PersonPageService service;

    @GetMapping
    public String getAllPersons(Model model) {
        List<PersonPageDto> persons = service.findAll();
        model.addAttribute("persons", persons);
        return "persons-page.html";
    }

    @GetMapping("/{id}")
    public String getPersonPage(@PathVariable Long id, Model model) {
        Optional<PersonPageDto> personOpt = service.findById(id);
        if (personOpt.isEmpty()) {
            throw new NoSuchElementException();
        }

        model.addAttribute("person", personOpt.get());
        return "person-page.html";
    }

}