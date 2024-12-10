package ru.alex.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import ru.alex.dto.TaskDto;

import java.util.concurrent.ExecutionException;

@Slf4j
@Component
public class KafkaTaskProducer {
    private final KafkaTemplate<String, TaskDto> template;

    public KafkaTaskProducer(KafkaTemplate<String, TaskDto> template) {
        this.template = template;
    }

    public void sendTaskStatusUpdate(TaskDto taskDto) {
        try {
            template.send("t1_task_topic", taskDto).get();
            template.flush();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
