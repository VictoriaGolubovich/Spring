package ru.gb.hw4.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gb.hw4.model.User;
import ru.gb.hw4.repository.UserRepository;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    public List<User> getAllUsers(){
        repository.save(new User(null, "Nick", "nick@mail.ru"));
        repository.save(new User(null, "John", "john@mail.ru"));
        repository.save(new User(null, "Kate", "kate@mail.ru"));
        return repository.findAll();
    }
    public User getUserById(Long id){
        return repository.findById(id);
    }

}
