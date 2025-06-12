package com.leeejjju.todo.todobackend.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "todos")
public class MongoTodo {

    @Id
    private String id;

    private String title;
    private boolean completed;

    public MongoTodo() {}

    public MongoTodo(String title, boolean completed) {
        this.title = title;
        this.completed = completed;
    }

    // Getter, Setter
}
