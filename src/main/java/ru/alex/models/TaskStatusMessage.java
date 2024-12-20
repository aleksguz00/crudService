package ru.alex.models;

import lombok.Getter;
import lombok.Setter;
import ru.alex.dto.TaskDto;

public class TaskStatusMessage {
    private Long taskId;
    private TaskStatus taskStatus;

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public TaskStatus getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(TaskStatus taskStatus) {
        this.taskStatus = taskStatus;
    }

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
