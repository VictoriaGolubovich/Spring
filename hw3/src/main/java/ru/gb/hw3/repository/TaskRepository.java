package ru.gb.hw3.repository;

import org.springframework.stereotype.Repository;
import ru.gb.hw3.model.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class TaskRepository {
    private static Long sequence = 1L;
    private final List<Task> tasks = new ArrayList<>();

    public Optional<Task> getById(Long id) {
        return tasks.stream()
                .filter(p -> Objects.equals(p.getId(), id))
                .findFirst();
    }

    public List<Task> getAll() {
        return List.copyOf(tasks);
    }

    public Task create(Task task) {
        task.setId(sequence++);
        tasks.add(task);
        return task;
    }

    public void delete(Long id) {
        tasks.stream()
                .filter(p -> Objects.equals(p.getId(), id))
                .findFirst()
                .ifPresent(tasks::remove);
    }

}
