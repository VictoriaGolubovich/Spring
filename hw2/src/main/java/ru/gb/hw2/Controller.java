package ru.gb.hw2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    private final Service service;
    @Autowired
    public Controller(Service service){
        this.service = service;
    }
    @GetMapping
    public String hello(){
        return service.getGreeting();
    }
}