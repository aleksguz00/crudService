package ru.alex.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private Long userId;
    private TaskStatus status;

    public boolean isStatusChanged(Task otherTask) {
        return status != otherTask.getStatus();
    }

    @Override
    public String toString() {
        return "Task: id = " + id + ",\n" +
                " title = " + title + ",\n" +
                " description = " + description + ",\n" +
                " userId = " + userId + "\n\n";
    }
}