package ru.gb.hw5.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gb.hw5.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

}
