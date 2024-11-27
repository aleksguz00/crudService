package ru.alex.util;

import ru.alex.dto.TaskDto;
import ru.alex.models.Task;

public class TaskMapper {

    public static Task toEntity(TaskDto dto) {
        Task task = new Task();

        task.setId(dto.getId());
        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());
        task.setUserId(dto.getUserId());
        task.setStatus(dto.getStatus());

        return task;
    }

    public static TaskDto toDto(Task task) {
        TaskDto dto = new TaskDto();

        dto.setId(task.getId());
        dto.setTitle(task.getTitle());
        dto.setDescription(task.getDescription());
        dto.setUserId(task.getUserId());
        dto.setStatus(task.getStatus());

        return dto;
    }
}
