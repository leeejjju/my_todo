package com.leeejjju.todo.todobackend.service;

import com.leeejjju.todo.todobackend.dto.TodoEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.leeejjju.todo.todobackend.domain.MongoTodo;
import com.leeejjju.todo.todobackend.repository.MongoTodoRepository;
import com.leeejjju.todo.todobackend.controller.TodoController;

@Service
public class TodoKafkaConsumer {

    private final MongoTodoRepository mongoTodoRepository;
    public TodoKafkaConsumer(MongoTodoRepository mongoTodoRepository) {
        this.mongoTodoRepository = mongoTodoRepository;
    }

    @KafkaListener(topics = "todo-events", groupId = "todo-group")
    public void consumeEvent(TodoEvent event) {
        System.out.println("[Consumer] Receive event: " + event.getEventType() + " - " + event.getTitle());

        // TODO 여기에 MongoDB에 동기화하는 로직 작성
        switch (event.getEventType()) {
            case "CREATE":
            case "UPDATE":
            case "TOGGLE":
                MongoTodo mongoTodo = new MongoTodo();
                mongoTodo.setId(event.getTodoId().toString());
                mongoTodo.setTitle(event.getTitle());
                mongoTodo.setCompleted(event.isCompleted());
                mongoTodoRepository.save(mongoTodo);
                System.out.println("[Consumer] save the data to MongoDB: " + event.getEventType() + " - " +  mongoTodo.getTitle());
                break;

            case "DELETE":
                mongoTodoRepository.deleteById(event.getTodoId().toString());
                System.out.println("[Consumer] deleted the data to MongoDB ");
                break;
            default:
                System.out.println("unknown event: " + event.getEventType());
        }

    }
    // refresh를 어케 하게하지.......

}
