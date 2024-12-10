package ru.alex.dto;

import lombok.Getter;
import lombok.Setter;
import ru.alex.models.TaskStatus;

public class TaskDto {
    private Long id;
    private String title;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    private String description;
    private Long userId;
    private TaskStatus status;

    public TaskDto() {}

    @Override
    public String toString() {
        return "TaskDto: id = " + id + ",\n" +
                " title = " + title + ",\n" +
                " description = " + description + ",\n" +
                " userId = " + userId + "\n\n";
    }
}
