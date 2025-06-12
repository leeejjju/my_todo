package com.leeejjju.todo.todobackend;

import com.leeejjju.todo.todobackend.repository.TodoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;


@CrossOrigin(origins = "http://localhost:3000") // 프론트랑 연결용이래...
@RestController
@RequestMapping("/api/todos")
public class TodoController {

    // 임시 저장소: 메모리 리스트 (DB를 대체)
//    private List<Todo> todoList = new ArrayList<>();
//    private Long nextId = 1L;

    private TodoRepository todoRepository; // 이게 찐 DB!!
    public TodoController(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    //GET 요청시 데이터 목록 반환
    @GetMapping
    public List<Todo> getTodos() {
        //TODO DB연결해서 데이터로 todos 채우기
        // 일단은 임시로 메모리에 있는 저거 반환...
//        return todoList;
        return todoRepository.findAll();
    }

    // POST 요청시 Body로 전달된 json 데이터를 DB에 저장하고 그 객체 반환
    @PostMapping
    public Todo addTodo(@RequestBody Todo newTodo){
        // 파라미터의 @RequestBody는 JSON을 자바 객체로 자동 변환해준대(대박이다)

        //TODO 실제로는 디비에 저장해야지요
//        todo.setId(nextId++);
//        if (!todo.isCompleted()){
//            todo.setCompleted(false); // 혹시 null인 경우 채워주기
//        }
//        todoList.add(todo);
//        return todo;
        return todoRepository.save(newTodo);
    }

    // DELETE 요청시 같이 들어온 id에 해당하는 데이터를 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable Long id) {

        if(todoRepository.existsById(id)){
            todoRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.notFound().build();
        }

    }

    // PATCH 요청 -> title 이라는 엔드포인트 : 내용 수정
    @PatchMapping("/{id}/title")
    public ResponseEntity<Todo> updateTitle(@PathVariable Long id, @RequestBody Map<String, String> request) {

        return todoRepository.findById(id).map(
                todo -> {
                    todo.setTitle(request.get("title"));
                    return ResponseEntity.ok(todoRepository.save(todo));
                }
        ).orElse(ResponseEntity.notFound().build());

//        for (Todo todo : todoList) {
//            if (todo.getId().equals(id)) { //이거 근데 DB면 쿼리 하나로 뚝딱 아님?
//                todo.setTitle(request.get("title"));
//                return ResponseEntity.ok(todo);
//            }
//        }
//        return ResponseEntity.notFound().build();
    }

    // PATCH 요청 -> toggle 이라는 엔드포인트 : 완료/미완료 변경
    @PatchMapping("/{id}/toggle")
    public ResponseEntity<Todo> toggleCompleted(@PathVariable Long id) {

        return todoRepository.findById(id).map(
                todo -> {
                    todo.setCompleted(!todo.isCompleted());
                    return ResponseEntity.ok(todoRepository.save(todo));
                }
        ).orElse(ResponseEntity.notFound().build());

//        for (Todo todo : todoList) {
//            if (todo.getId().equals(id)) {
//                todo.setCompleted(!todo.isCompleted()); // true ↔ false
//                return ResponseEntity.ok(todo);
//            }
//        }
//        return ResponseEntity.notFound().build();
    }





}
