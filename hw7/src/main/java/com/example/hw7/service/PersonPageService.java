package com.example.hw7.service;

import lombok.RequiredArgsConstructor;
import com.example.hw7.model.Person;
import org.springframework.stereotype.Service;
import com.example.hw7.page.PersonPageDto;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class PersonPageService {
    private final PersonService personService;

    public List<PersonPageDto> findAll() {
        return personService.findAll().stream()
                .map(this::convert)
                .toList();
    }

    public Optional<PersonPageDto> findById(Long id) {
        return personService.findById(id)
                .map(this::convert);
    }

    private PersonPageDto convert(Person person) {
        PersonPageDto pageParameters = new PersonPageDto();
        pageParameters.setId(String.valueOf(person.getId()));
        pageParameters.setName(String.valueOf(person.getName()));
        pageParameters.setEmail(String.valueOf(person.getEmail()));

        return pageParameters;
    }

}