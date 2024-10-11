package org.example.controller;

import org.example.model.Person;
import org.example.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PersonControllerTest {

    @Autowired
    PersonRepository repository;
    @LocalServerPort
    private int port;
    private RestClient restClient;

    @BeforeEach
    void beforeEach(){
        restClient = RestClient.create("http://localhost:" + port);
    }

    @Test
    void getByIdNotFound(){
        assertThrows(HttpClientErrorException.NotFound.class, () -> {
            restClient.get()
                    .uri("/persons/-2")
                    .retrieve()
                    .toBodilessEntity();
        });
    }

    @Test
    void getByIdAllOk() {
        Person expected = new Person();
        expected.setName("personName");
        expected = repository.save(expected);

        ResponseEntity<Person> actual = restClient.get()
                .uri("/persons/" + expected.getId())
                .retrieve()
                .toEntity(Person.class);

        assertEquals(HttpStatus.OK, actual.getStatusCode());
        Person responseBody = actual.getBody();
        assertNotNull(responseBody);
        assertEquals(expected.getId(), responseBody.getId());
        assertEquals(expected.getName(), responseBody.getName());
    }

    @Test
    void testCreate(){
        Person toCreate = new Person();
        toCreate.setName("NewName");

        ResponseEntity<Person> response = restClient.post()
                .uri("/persons")
                .body(toCreate)
                .retrieve()
                .toEntity(Person.class);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        Person responseBody = response.getBody();
        assertNotNull(responseBody);
        assertNotNull(responseBody.getId());
        assertEquals(responseBody.getName(), toCreate.getName());

        assertTrue(repository.existsById(responseBody.getId()));
    }

    @Test
    void deleteById(){
        Person toDelete = new Person();
        toDelete.setName("NewName");
        toDelete = repository.save(toDelete);

        ResponseEntity<Void> response = restClient.delete()
                .uri("/persons/" +toDelete.getId())
                .retrieve()
                .toBodilessEntity();
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());

        assertFalse(repository.existsById(toDelete.getId()));
    }
}