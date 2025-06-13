package com.leeejjju.todo.todobackend.controller;
import com.leeejjju.todo.todobackend.domain.MariaTodo;
import com.leeejjju.todo.todobackend.repository.MariaTodoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@CrossOrigin(origins = "http://localhost:3000") // 프론트랑 연결용이래...
@RestController
@RequestMapping("/api/todos/maria")
public class MariaTodoController {

    private final MariaTodoRepository mariaTodoRepository; // 이게 찐 DB!!

    public MariaTodoController(MariaTodoRepository mariaTodoRepository) {
        this.mariaTodoRepository = mariaTodoRepository;
    }

    //GET 요청시 데이터 목록 반환
    @GetMapping
    public List<MariaTodo> getTodos() {
        return mariaTodoRepository.findAll();
    }

    // POST 요청시 Body로 전달된 json 데이터를 DB에 저장하고 그 객체 반환
    @PostMapping
    public MariaTodo addTodo(@RequestBody MariaTodo newTodo){
        // 파라미터의 @RequestBody는 JSON을 자바 객체로 자동 변환해준대(대박이다)
        return mariaTodoRepository.save(newTodo);
    }

    // DELETE 요청시 같이 들어온 id에 해당하는 데이터를 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable Long id) {

        if(mariaTodoRepository.existsById(id)){
            mariaTodoRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.notFound().build();
        }

    }

    // PATCH 요청 -> title 이라는 엔드포인트 : 내용 수정
    @PatchMapping("/{id}/title")
    public ResponseEntity<MariaTodo> updateTitle(@PathVariable Long id, @RequestBody Map<String, String> request) {

        return mariaTodoRepository.findById(id).map(
                todo -> {
                    todo.setTitle(request.get("title"));
                    return ResponseEntity.ok(mariaTodoRepository.save(todo));
                }
        ).orElse(ResponseEntity.notFound().build());

    }

    // PATCH 요청 -> toggle 이라는 엔드포인트 : 완료/미완료 변경
    @PatchMapping("/{id}/toggle")
    public ResponseEntity<MariaTodo> toggleCompleted(@PathVariable Long id) {

        return mariaTodoRepository.findById(id).map(
                todo -> {
                    todo.setCompleted(!todo.isCompleted());
                    return ResponseEntity.ok(mariaTodoRepository.save(todo));
                }
        ).orElse(ResponseEntity.notFound().build());

    }
}
