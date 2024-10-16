package org.example.service;

import org.example.model.Person;
import org.example.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.junit.jupiter.api.Assertions;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;

@ActiveProfiles("test")
@SpringBootTest()
class PersonServiceTest {

    @Autowired
    PersonRepository repository;

    @Autowired
    PersonService service;

    @Test
    void findByIdEmpty() {
        assertFalse(repository.existsById(2L));
        assertTrue(service.findById(2L).isEmpty());
    }

    @Test
    void findByIdPresent() {
        Person person = new Person();
        person.setName("personName");
        person = repository.save(person);

        Optional<Person> actual = service.findById(person.getId());
        assertTrue(actual.isPresent());
        assertEquals(actual.get().getId(), person.getId());
        assertEquals(actual.get().getName(), person.getName());
    }
}