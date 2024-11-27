package ru.alex.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import ru.alex.dto.TaskDto;
import ru.alex.models.TaskStatusMessage;
import ru.alex.service.NotificationService;

@Slf4j
@Component
public class KafkaTaskConsumer {
    private final NotificationService notificationService;

    @Autowired
    public KafkaTaskConsumer(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @KafkaListener(topics = "t1_task_topic", groupId = "t1-task")
    public void listen(@Payload TaskDto taskDto, Acknowledgment ack) {
        try {
            TaskStatusMessage message = new TaskStatusMessage(taskDto);

            notificationService.sendNotification(message);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ack.acknowledge();
        }
    }
}
