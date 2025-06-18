package com.leeejjju.todo.todobackend.service;

import com.leeejjju.todo.todobackend.dto.TodoEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class TodoKafkaProducer {

    private final KafkaTemplate<String, TodoEvent> kafkaTemplate;
    private static final String TOPIC = "todo-events";

    public TodoKafkaProducer(KafkaTemplate<String, TodoEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendTodoEvent(TodoEvent event) {
        kafkaTemplate.send(TOPIC, event);
        System.out.println("[Producer] Send event: " + event.getEventType() + " - " + event.getTitle());
    }
}
