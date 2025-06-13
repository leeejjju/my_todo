package com.leeejjju.todo.todobackend.controller;

import com.leeejjju.todo.todobackend.domain.MongoTodo;
import com.leeejjju.todo.todobackend.repository.MongoTodoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000") // 프론트랑 연결용이래...
@RestController
@RequestMapping("/api/todos/mongo")
public class MongoTodoController_tmp {

    private final MongoTodoRepository mongoTodoRepository;

    public MongoTodoController_tmp(MongoTodoRepository mongoTodoRepository) {
        this.mongoTodoRepository = mongoTodoRepository;
    }

    //GET 요청시 데이터 목록 반환
    @GetMapping
    public List<MongoTodo> getTodos() {
        return mongoTodoRepository.findAll();
    }

    // POST 요청시 Body로 전달된 json 데이터를 DB에 저장하고 그 객체 반환
    @PostMapping
    public MongoTodo addTodo(@RequestBody MongoTodo newTodo){
        // 파라미터의 @RequestBody는 JSON을 자바 객체로 자동 변환해준대(대박이다)
        return mongoTodoRepository.save(newTodo);
    }

    // DELETE 요청시 같이 들어온 id에 해당하는 데이터를 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable String id) {

        if(mongoTodoRepository.existsById(id)){
            mongoTodoRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.notFound().build();
        }

    }

    // PATCH 요청 -> title 이라는 엔드포인트 : 내용 수정
    @PatchMapping("/{id}/title")
    public ResponseEntity<MongoTodo> updateTitle(@PathVariable String id, @RequestBody Map<String, String> request) {

        return mongoTodoRepository.findById(id).map(
                todo -> {
                    todo.setTitle(request.get("title"));
                    return ResponseEntity.ok(mongoTodoRepository.save(todo));
                }
        ).orElse(ResponseEntity.notFound().build());

    }

    // PATCH 요청 -> toggle 이라는 엔드포인트 : 완료/미완료 변경
    @PatchMapping("/{id}/toggle")
    public ResponseEntity<MongoTodo> toggleCompleted(@PathVariable String id) {

        return mongoTodoRepository.findById(id).map(
                todo -> {
                    todo.setCompleted(!todo.isCompleted());
                    return ResponseEntity.ok(mongoTodoRepository.save(todo));
                }
        ).orElse(ResponseEntity.notFound().build());

    }
}
