package ru.gb.hw5.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.gb.hw5.model.Person;
import ru.gb.hw5.service.PersonService;

@Controller
@RequestMapping("/persons")
public class PersonController {
    @Autowired
    private PersonService service;

    public PersonController(PersonService service) {
        this.service = service;
    }

    @GetMapping
    public String listUsers(Model model){
        model.addAttribute("persons", service.getAllPersons());
        return "persons";
    }

    @GetMapping("/{id}")
    public String getPerson(@PathVariable Long id, Model model){
        if(service.getPersonById(id)==null){
            return "not-found";
        }
        model.addAttribute("person", service.getPersonById(id));
        return "personProfile";
    }

    @PostMapping
    public ResponseEntity<Person> create(@RequestBody Person person) {
        person = service.create(person);
        return ResponseEntity.status(HttpStatus.CREATED).body(person);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
