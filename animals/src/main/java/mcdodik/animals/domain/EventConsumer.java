package mcdodik.animals.domain;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class EventConsumer {

    @KafkaListener(topics = "animal-events", groupId = "animal-group")
    public void consume(String message) {
        System.out.println("Received event: " + message);
        // Добавьте здесь логику обработки события
    }
}