package ru.gb.hw3.service;

import org.springframework.stereotype.Service;
import ru.gb.hw3.model.Task;
import ru.gb.hw3.repository.TaskRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    private final TaskRepository repository;

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    public Optional<Task> getById(Long id) {
        return repository.getById(id);
    }

    public List<Task> getAll() {
        return repository.getAll();
    }

    public Task create(Task task) {
        return repository.create(task);
    }

    public void delete(Long id) {
        repository.delete(id);
    }


}
