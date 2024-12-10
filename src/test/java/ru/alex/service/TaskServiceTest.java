package ru.alex.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.alex.models.Task;
import ru.alex.models.TaskStatus;
import ru.alex.repository.TaskRepository;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TaskServiceTest {
    @Autowired
    private TaskService taskService;

    @Autowired
    private TaskRepository taskRepository;

    Task task;

    @BeforeEach
    void setUp() {
        taskRepository.deleteAll();

        Long taskId = 1L;

        task = new Task();
        task.setId(taskId);
        task.setTitle("some task");
        task.setDescription("some description");
        task.setUserId(1L);
        task.setStatus(TaskStatus.PENDING);

        taskRepository.save(task);
    }

    @Test
    @DisplayName("Проверка создания задачи")
    void createTask() {
        Task createdTask = taskService.createTask(task);
        assertNotNull(createdTask);
    }

    @Test
    @DisplayName("Проверка исключения при неправильном userId")
    void createTaskInvalidUserId() {
        Task newTask = new Task();
        newTask.setTitle("new task");
        newTask.setDescription("new description");
        newTask.setUserId(-1L);
        newTask.setStatus(TaskStatus.PENDING);

        assertThrows(IllegalArgumentException.class, () -> taskService.createTask(newTask));
    }

    @Test
    @DisplayName("Проверка поиска задачи по id")
    void getTaskById() {
        Long taskId = 1L;

        Optional<Task> task = taskService.getTaskById(taskId);

        assertTrue(task.isPresent());
    }

    @Test
    @DisplayName("Проверка получения всех задач")
    void getAllTasks() {
        List<Task> tasks = taskService.getAllTasks();

        assertEquals(1, tasks.size());
    }

    @Test
    @DisplayName("Проверка обновления задачи")
    void updateTask() {
        Long taskId = 1L;

        Task updatedTask = new Task();
        updatedTask.setId(taskId);
        updatedTask.setTitle("updated task");
        updatedTask.setDescription("updated description");
        updatedTask.setUserId(1L);
        updatedTask.setStatus(TaskStatus.IN_PROGRESS);

        Task updatedTaskResult = taskService.updateTask(taskId, updatedTask);

        assertNotNull(updatedTaskResult);
    }

    @Test
    @DisplayName("Проверка исключения при обновлении задачи с неправильным userId")
    void updateTaskInvalidUserId() {
        Long taskId = 1L;

        Task newTask = new Task();
        newTask.setTitle("new task");
        newTask.setDescription("new description");
        newTask.setUserId(-1L);
        newTask.setStatus(TaskStatus.PENDING);

        assertThrows(IllegalArgumentException.class, () -> taskService.updateTask(taskId, newTask));
    }

    @Test
    @DisplayName("Проверка удаления задачи")
    void deleteTask() {
        Long taskId = 1L;

        assertTrue(taskService.getTaskById(taskId).isPresent());
        taskService.deleteTask(taskId);
        assertFalse(taskService.getTaskById(taskId).isPresent());
    }
}