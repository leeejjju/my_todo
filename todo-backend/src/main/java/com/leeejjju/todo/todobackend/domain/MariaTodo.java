package com.leeejjju.todo.todobackend.domain;
import jakarta.persistence.*;
import jakarta.persistence.GenerationType;


// 이 @Entity라는 어노테이션을 붙여놓으면 Spring JPA가 이 클래스를 DB 테이블로 취급 (와...)
@Entity
@Table(name="todos")
public class MariaTodo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private boolean completed;

    //생성자
    public MariaTodo() {
        this.completed = false;
    }
    public MariaTodo(Long id, String title) {
        this.id = id;
        this.title = title;
        this.completed = false;
    }
    public MariaTodo(Long id, String title, boolean completed) {
        this.id = id;
        this.title = title;
        this.completed = completed;
    }

    // Getter
    public Long getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public boolean isCompleted() {
        return completed;
    }

    // Setter
    public void setId(Long id) {
        this.id = id;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

}
