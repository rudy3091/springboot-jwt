package com.rudy.jwtloginexample;

import com.rudy.jwtloginexample.domain.Todo.Todo;
import com.rudy.jwtloginexample.domain.Todo.TodoRepository;
import com.rudy.jwtloginexample.domain.User.Role;
import com.rudy.jwtloginexample.domain.User.User;
import com.rudy.jwtloginexample.domain.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class JwtLoginExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(JwtLoginExampleApplication.class, args);
    }

    @Bean
    public CommandLineRunner loadData(UserRepository userRepository, TodoRepository todoRepository) {
        return (args -> {
            userRepository.save(new User("admin@test.com", "testadmin", "testaliasadmin", Role.ADMIN));
            userRepository.save(new User("user@test.com", "testuser", "testaliasuser", Role.USER));
            todoRepository.save(new Todo(1L, 1L, "content"));
            todoRepository.save(new Todo(2L, 2L, "content2"));
        });
    }
}
