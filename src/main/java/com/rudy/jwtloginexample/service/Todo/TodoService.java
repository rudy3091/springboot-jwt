package com.rudy.jwtloginexample.service.Todo;

import com.rudy.jwtloginexample.domain.Todo.Todo;
import com.rudy.jwtloginexample.domain.Todo.TodoRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class TodoService {

    private TodoRepository todoRepository;

    public List<Todo> findAllByUserId(Long userId) {
        return this.todoRepository.findByUserId(userId);
    }

    public Todo save(Todo todo) {
        this.todoRepository.save(todo);
        return todo;
    }
}
