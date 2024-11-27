package ru.alex.models;

import lombok.Getter;
import lombok.Setter;
import ru.alex.dto.TaskDto;

@Getter
@Setter
public class TaskStatusMessage {
    private Long taskId;
    private TaskStatus taskStatus;

    public TaskStatusMessage() {}

    public TaskStatusMessage(TaskDto taskDto) {
        System.out.println(taskDto);

        System.out.println(taskDto.getStatus());
        taskId = taskDto.getId();
        taskStatus = taskDto.getStatus();
    }

    @Override
    public String toString() {
        return "TaskStatusMessage{" +
                "taskId=" + taskId +
                ", taskStatus=" + taskStatus +
                '}';
    }
}
