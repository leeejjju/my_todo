package com.leeejjju.todo.todobackend.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "todos")
public class MongoTodo {

    @Id
    private String id;
    private String title;
    private boolean completed;

    // 생성자
    public MongoTodo() { this.completed = false; }

    // Getter, Setter
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public void setTitle(String title) { this.title = title; }
    public String getTitle() { return title; }

    public void setCompleted(boolean completed) { this.completed = completed; }
    public boolean isCompleted() { return completed; }
}
