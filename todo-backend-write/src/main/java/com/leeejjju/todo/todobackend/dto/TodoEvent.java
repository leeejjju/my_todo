package com.leeejjju.todo.todobackend.dto;
import java.time.LocalDateTime;

public class TodoEvent {

    private String eventType;
    private Long todoId;
    private String title;
    private boolean completed;
    private String timestamp; // 또는 LocalDateTime 등

    public  TodoEvent(){
        // 역직렬화를 위한 기본 생성자 -> jackson이 setter를 이용해서 알아서 채운대... 아 괜히 내가 손대가지고...ㅋㅋ...
    }
    // 기본 생성자 + getter/setter

    // create랑 title, toggle 수정용 생성자
    public TodoEvent(String eventType, Long todoId, String title, boolean completed) {
        setEventType(eventType);
        setTodoId(todoId);
        setTitle(title);
        setCompleted(completed);
        setTimestamp(LocalDateTime.now().toString());
    }

    // delete용 생성자
    public TodoEvent(String eventType, Long todoId) {
        setEventType(eventType);
        setTodoId(todoId);
    }

    // getters & setters
    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public Long getTodoId() {
        return todoId;
    }

    public void setTodoId(Long todoId) {
        this.todoId = todoId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
