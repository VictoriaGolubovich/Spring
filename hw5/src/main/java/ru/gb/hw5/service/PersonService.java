package ru.gb.hw5.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gb.hw5.model.Person;
import ru.gb.hw5.repository.PersonRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {
    @Autowired
    private PersonRepository repository;
    public List<Person> getAllPersons(){
        repository.save(new Person(null, "Nick", "nick@mail.ru"));
        repository.save(new Person(null, "John", "john@mail.ru"));
        repository.save(new Person(null, "Kate", "kate@mail.ru"));
        return repository.findAll();
    }


    public Person getPersonById(Long id){
        return repository.findById(id).get();
    }
    public Person create(Person person){
        return repository.save(person);
    }
    public void delete(Long id){
        repository.deleteById(id);
    }

}
