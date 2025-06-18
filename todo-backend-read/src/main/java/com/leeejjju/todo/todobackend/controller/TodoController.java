package com.leeejjju.todo.todobackend.controller;
import com.leeejjju.todo.todobackend.domain.MongoTodo;
import com.leeejjju.todo.todobackend.repository.MongoTodoRepository;

import com.leeejjju.todo.todobackend.dto.TodoEvent;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000") // 프론트랑 연결용이래...
@RestController
@RequestMapping("/api/todos")
public class TodoController {
    private final MongoTodoRepository mongoTodoRepository;
    public TodoController(MongoTodoRepository mongoTodoRepository) {
        this.mongoTodoRepository = mongoTodoRepository;
    }

    //GET 요청시 데이터 목록 반환
    @GetMapping
    public List<MongoTodo> getTodos() {
        return mongoTodoRepository.findAll();
    }

}
