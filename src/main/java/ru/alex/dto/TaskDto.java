package ru.alex.dto;

import lombok.Getter;
import lombok.Setter;
import ru.alex.models.TaskStatus;

@Setter
@Getter
public class TaskDto {
    private Long id;
    private String title;
    private String description;
    private Long userId;
    private TaskStatus status;

    public TaskDto() {}

//    public TaskDto(Long id, String title, String description, Long userId, TaskStatus status) {
//        this.id = id;
//        this.title = title;
//        this.description = description;
//        this.userId = userId;
//        this.status = status;
//    }

    @Override
    public String toString() {
        return "TaskDto: id = " + id + ",\n" +
                " title = " + title + ",\n" +
                " description = " + description + ",\n" +
                " userId = " + userId + "\n\n";
    }
}
