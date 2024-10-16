package org.example.service;

import org.example.client.PersonResponse;
import org.example.controller.PersonPageDto;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClient;


import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class PersonPageService {

    private final DiscoveryClient discoveryClient;

    public PersonPageService(DiscoveryClient discoveryClient) {
        this.discoveryClient = discoveryClient;
    }

    private RestClient restClient() {
        List<ServiceInstance> instances = discoveryClient.getInstances("PERSON-REST");
        int instancesCount = instances.size();
        int instanceIndex = ThreadLocalRandom.current().nextInt(0, instancesCount);

        ServiceInstance instance = instances.get(instanceIndex);
        String uri = "http://" + instance.getHost() + ":" + instance.getPort();
        System.out.println("URI = " + uri);
        return RestClient.create(uri);
    }

    public List<PersonPageDto> findAll() {
        List<PersonResponse> persons = null;
        int attempts = 5;
        while (attempts-- > 0) {
            try {
                persons = restClient().get()
                        .uri("/persons")
                        .retrieve()
                        .body(new ParameterizedTypeReference<List<PersonResponse>>() {
                        });
                break;
            } catch (HttpServerErrorException e) {
                // ignore
            }
        }
        if (persons == null) {
            throw new RuntimeException("oops");
        }

        List<PersonPageDto> result = new ArrayList<>();
        for (PersonResponse person : persons) {
            PersonPageDto personPageDto = new PersonPageDto();
            personPageDto.setId(String.valueOf(person.getId()));
            personPageDto.setName(person.getName());
            personPageDto.setEmail(person.getEmail());

            result.add(personPageDto);
        }
        return result;
    }

    public Optional<PersonPageDto> findById(Long id) {
        try {
            PersonResponse person = restClient().get()
                    .uri("/persons/" + id)
                    .retrieve()
                    .body(PersonResponse.class);

            PersonPageDto personPageDto = new PersonPageDto();
            personPageDto.setId(String.valueOf(person.getId()));
            personPageDto.setName(person.getName());
            personPageDto.setEmail(person.getEmail());

            return Optional.of(personPageDto);
        } catch (HttpClientErrorException.NotFound e) {
            return Optional.empty();
        }
    }

}

