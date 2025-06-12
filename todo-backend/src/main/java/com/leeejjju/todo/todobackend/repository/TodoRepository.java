package com.leeejjju.todo.todobackend.repository;
import com.leeejjju.todo.todobackend.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

// Spring이 알아서 구현체를 만들어주는 JPA 기반 DAO(데이터 접근 객체)
// 그니까 이 객체의 함수를 통해 SQL문을 안 쓰고도 DB랑 소통이 가능하다는... 말도안되게 편하네 ;;
public interface TodoRepository extends JpaRepository<Todo, Long> {
    // 기본적인 CRUD 기능은 이미 상속받음! 필요한 경우 커스텀 쿼리도 여기에 추가 가능.
//    TodoRepository.findAll() → SELECT * FROM todo
//    TodoRepository.save(todo) → INSERT INTO todo (...) VALUES (...)
//    TodoRepository.deleteById(id) → DELETE FROM todo WHERE id = ?
}

