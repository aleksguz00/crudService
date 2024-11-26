package ru.alex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.alex.dto.TaskDto;
import ru.alex.models.Task;
import ru.alex.service.TaskService;
import ru.alex.util.TaskMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public TaskDto createTask(@RequestBody TaskDto taskDto) {
        Task task = TaskMapper.toEntity(taskDto);
        Task createdTask = taskService.createTask(task);

        return TaskMapper.toDto(createdTask);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskDto> getTaskById(@PathVariable Long id) {
        Optional<Task> task = taskService.getTaskById(id);

        if (task.isPresent()) {
            return ResponseEntity.ok(TaskMapper.toDto(task.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public List<TaskDto> getAllTasks() {
        return taskService.getAllTasks().stream().map(TaskMapper::toDto).collect(Collectors.toList());
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskDto> updateTask(@PathVariable Long id, @RequestBody TaskDto taskDto) {
        Task task = TaskMapper.toEntity(taskDto);
        Task updatedTask = taskService.updateTask(id, task);
        return ResponseEntity.ok(TaskMapper.toDto(updatedTask));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }
}