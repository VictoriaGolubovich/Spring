package com.example.hw8.service;

import org.springframework.stereotype.Service;
import com.example.hw8.model.Person;
import com.example.hw8.repository.PersonRepository;
import java.util.List;
import java.util.Optional;

@Service
public class PersonService {
    private final PersonRepository repository;

    public PersonService(PersonRepository repository) {
        this.repository = repository;
    }
    public List<Person> findAll(){
        return repository.findAll();
    }

    public Optional<Person> findById(Long id){
        return repository.findById(id);
    }
    public Person save(Person person){
        return repository.save(person);
    }
    public void deleteById(Long id){
        repository.deleteById(id);
    }

}
