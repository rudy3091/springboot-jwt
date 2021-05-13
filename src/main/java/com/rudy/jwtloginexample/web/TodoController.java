package com.rudy.jwtloginexample.web;

import com.rudy.jwtloginexample.config.jwt.JwtProvider;
import com.rudy.jwtloginexample.domain.Todo.Todo;
import com.rudy.jwtloginexample.service.Todo.TodoService;
import com.rudy.jwtloginexample.web.dto.TodoCreateResponseDto;
import com.rudy.jwtloginexample.web.dto.TodoRequestDto;
import com.rudy.jwtloginexample.web.dto.TodoResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
public class TodoController {

    private JwtProvider jwtProvider;
    private TodoService todoService;

    @GetMapping("/api/todos")
    public ResponseEntity<TodoResponseDto> get(HttpServletRequest req) {
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            String accessToken = Arrays.stream(cookies)
                    .filter(c -> c.getName().equals("access_token"))
                    .collect(Collectors.toList())
                    .get(0)
                    .getValue();

            Long userId = this.jwtProvider.getUserPk(accessToken);
            List<Todo> todos = this.todoService.findAllByUserId(userId);
            TodoResponseDto todoResponseDto = new TodoResponseDto(todos
                .stream().map(Todo::getContent).collect(Collectors.toList()), userId);

            return new ResponseEntity<>(todoResponseDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/api/todos")
    public ResponseEntity<TodoCreateResponseDto> post(@RequestBody TodoRequestDto todoRequestDto, HttpServletRequest req) {
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            String accessToken = Arrays.stream(cookies)
                    .filter(c -> c.getName().equals("access_token"))
                    .collect(Collectors.toList())
                    .get(0)
                    .getValue();

            Long userId = this.jwtProvider.getUserPk(accessToken);
            TodoCreateResponseDto todo = new TodoCreateResponseDto(this.todoService.save(todoRequestDto.toEntity(userId)));

            return new ResponseEntity<>(todo, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
        }
    }
}
