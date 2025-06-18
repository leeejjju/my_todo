package com.leeejjju.todo.todobackend.repository;
import com.leeejjju.todo.todobackend.domain.MongoTodo;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface MongoTodoRepository extends MongoRepository<MongoTodo, String> {
}
