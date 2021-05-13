package com.rudy.jwtloginexample.web.dto;

import com.rudy.jwtloginexample.domain.Todo.Todo;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class TodoResponseDto {
    private List<String> contents;
    private Long userId;
}
