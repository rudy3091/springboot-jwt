package com.rudy.jwtloginexample.web.dto;

import com.rudy.jwtloginexample.domain.Todo.Todo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class TodoRequestDto {
    private String content;

    public Todo toEntity(Long userId) {
        return Todo.builder()
                .userId(userId)
                .content(this.content)
                .build();
    }
}
