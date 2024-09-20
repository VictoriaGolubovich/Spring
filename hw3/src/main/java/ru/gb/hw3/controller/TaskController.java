package ru.gb.hw3.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.hw3.model.Task;
import ru.gb.hw3.service.TaskService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }

    @GetMapping("/{id}") // получить все
    public ResponseEntity<Task> get(@PathVariable Long id) {
        Optional<Task> pr = service.getById(id);
        if (pr.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(pr.get());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping // получить все
    public ResponseEntity<List<Task>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @PostMapping // создание нового ресурса
    public ResponseEntity<Task> create(@RequestBody Task task) {
        task = service.create(task);
        // 201 Created
        return ResponseEntity.status(HttpStatus.CREATED).body(task);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        // 204 No Content
        return ResponseEntity.noContent().build();
    }
}
