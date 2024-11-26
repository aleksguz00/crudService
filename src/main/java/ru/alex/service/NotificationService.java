package ru.alex.service;

import org.springframework.stereotype.Service;
import ru.alex.models.TaskStatusMessage;

@Service
public class NotificationService {
    public void sendNotification(TaskStatusMessage message) {
        System.out.println("Task " + message.getTaskId() + " status has been changed to "
                + message.getTaskStatus().toString());
    }
}
