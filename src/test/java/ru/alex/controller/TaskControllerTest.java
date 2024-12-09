package ru.alex.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.alex.models.Task;
import ru.alex.models.TaskStatus;
import ru.alex.repository.TaskRepository;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class TaskControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TaskRepository taskRepository;

    @BeforeEach
    void setUp() {
        taskRepository.deleteAll();
        createTestTasks();
    }

    @Test
    void createTask() throws Exception {
        String newTaskJson = "{\"title\":\"New Task\"," +
                " \"description\":\"Task Description\"," +
                " \"userId\":1," +
                " \"status\":\"PENDING\"}";

        mockMvc.perform(MockMvcRequestBuilders.post("/tasks")
                        .contentType("application/json")
                        .content(newTaskJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("New Task"))
                .andExpect(jsonPath("$.description").value("Task Description"))
                .andExpect(jsonPath("$.userId").value(1))
                .andExpect(jsonPath("$.status").value("PENDING"));
    }

    @Test
    void getTaskById() throws Exception {
        Long taskId = 1L;

        String expectedTitle = "Task 1";
        String expectedDescription = "Task 1 Description";
        Long expectedUserId = 1L;
        String expectedStatus = "IN_PROGRESS";

        mockMvc.perform(MockMvcRequestBuilders.get("/tasks/{id}", taskId)
                        .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value(expectedTitle))
                .andExpect(jsonPath("$.description").value(expectedDescription))
                .andExpect(jsonPath("$.userId").value(expectedUserId))
                .andExpect(jsonPath("$.status").value(expectedStatus));

    }

    @Test
    void getAllTasks() throws Exception {
        String expectedTitle1 = "Task 1";
        String expectedDescription1 = "Task 1 Description";
        Long expectedUserId1 = 1L;
        String expectedStatus1 = "IN_PROGRESS";

        String expectedTitle2 = "Task 2";
        String expectedDescription2 = "Task 2 Description";
        Long expectedUserId2 = 2L;
        String expectedStatus2 = "PENDING";

        String expectedTitle3 = "Task 3";
        String expectedDescription3 = "Task 3 Description";
        Long expectedUserId3 = 3L;
        String expectedStatus3 = "COMPLETED";

        mockMvc.perform(MockMvcRequestBuilders.get("/tasks")
                        .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value(expectedTitle1))
                .andExpect(jsonPath("$[0].description").value(expectedDescription1))
                .andExpect(jsonPath("$[0].userId").value(expectedUserId1))
                .andExpect(jsonPath("$[0].status").value(expectedStatus1))

                .andExpect(jsonPath("$[1].title").value(expectedTitle2))
                .andExpect(jsonPath("$[1].description").value(expectedDescription2))
                .andExpect(jsonPath("$[1].userId").value(expectedUserId2))
                .andExpect(jsonPath("$[1].status").value(expectedStatus2))

                .andExpect(jsonPath("$[2].title").value(expectedTitle3))
                .andExpect(jsonPath("$[2].description").value(expectedDescription3))
                .andExpect(jsonPath("$[2].userId").value(expectedUserId3))
                .andExpect(jsonPath("$[2].status").value(expectedStatus3));
    }

    @Test
    void updateTask() throws Exception {
        Long taskId = 1L;
        String updatedTitle = "Updated Task Title";
        String updatedDescription = "Updated Task Description";
        Long updatedUserId = 1L;
        String updatedStatus = "COMPLETED";

        String jsonRequest = String.format("{\"title\":\"%s\",\"description\":\"%s\",\"userId\":%d,\"status\":\"%s\"}",
                updatedTitle, updatedDescription, updatedUserId, updatedStatus);

        mockMvc.perform(MockMvcRequestBuilders.put("/tasks/{id}", taskId)
                        .contentType("application/json")
                        .content(jsonRequest))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value(updatedTitle))
                .andExpect(jsonPath("$.description").value(updatedDescription))
                .andExpect(jsonPath("$.userId").value(updatedUserId))
                .andExpect(jsonPath("$.status").value(updatedStatus));
    }

    @Test
    void deleteTask() throws Exception {
        Long taskId = 1L;

        mockMvc.perform(MockMvcRequestBuilders.get("/tasks/{id}", taskId)
                .contentType("application/json"))
                .andExpect(status().isOk());

        mockMvc.perform(MockMvcRequestBuilders.delete("/tasks/{id}", taskId)
                .contentType("application/json"))
                .andExpect(status().isNoContent());

        mockMvc.perform(MockMvcRequestBuilders.get("/tasks/{id}", taskId)
                .contentType("application/json"))
                .andExpect(status().isNotFound());
    }

    private void createTestTasks() {
        Task task1 = new Task();

        task1.setTitle("Task 1");
        task1.setDescription("Task 1 Description");
        task1.setUserId(1L);
        task1.setStatus(TaskStatus.IN_PROGRESS);

        taskRepository.save(task1);

        Task task2 = new Task();
        task2.setTitle("Task 2");
        task2.setDescription("Task 2 Description");
        task2.setUserId(2L);
        task2.setStatus(TaskStatus.PENDING);

        taskRepository.save(task2);

        Task task3 = new Task();
        task3.setTitle("Task 3");
        task3.setDescription("Task 3 Description");
        task3.setUserId(3L);
        task3.setStatus(TaskStatus.COMPLETED);

        taskRepository.save(task3);
    }
}