package com.rudy.jwtloginexample.web.dto;

import com.rudy.jwtloginexample.domain.Todo.Todo;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class TodoCreateResponseDto {
    private Long id;
    private Long userId;
    private String content;

    public TodoCreateResponseDto(Todo todo) {
        this.id = todo.getId();
        this.userId = todo.getUserId();
        this.content = todo.getContent();
    }
}
