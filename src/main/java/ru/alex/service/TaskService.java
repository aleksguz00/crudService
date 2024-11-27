package ru.alex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.alex.aspect.CheckIdExecution;
import ru.alex.aspect.ErrorExecution;
import ru.alex.aspect.GetterExecution;
import ru.alex.aspect.LogExecution;
import ru.alex.kafka.KafkaTaskProducer;
import ru.alex.models.Task;
import ru.alex.repository.TaskRepository;
import ru.alex.util.TaskMapper;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private final KafkaTaskProducer kafkaTaskProducer;

    @Autowired
    public TaskService(TaskRepository taskRepository, KafkaTaskProducer kafkaTaskProducer) {
        this.taskRepository = taskRepository;
        this.kafkaTaskProducer = kafkaTaskProducer;
    }

    @LogExecution
    @ErrorExecution
    public Task createTask(Task task) {
        if (task.getUserId() < 0) {
            throw new IllegalArgumentException("User id must be greater than 0");
        }

        return taskRepository.save(task);
    }

    @LogExecution
    @GetterExecution
    public Optional<Task> getTaskById(Long id) {
        return taskRepository.findById(id);
    }

    @LogExecution
    @GetterExecution
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    // TODO: Fix aspects and add id checking (if user with given id even exists)
    @LogExecution
    @CheckIdExecution
    public Task updateTask(Long id, Task task) {
        Task oldTask = taskRepository.findById(id).get();

        task.setId(id);

        if (oldTask.isStatusChanged(task)) {
            kafkaTaskProducer.sendTaskStatusUpdate(TaskMapper.toDto(task));
        }

        return taskRepository.save(task);
    }

    @LogExecution
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }
}
