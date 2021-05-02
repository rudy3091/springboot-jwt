package com.rudy.jwtloginexample.service.User;

import com.rudy.jwtloginexample.domain.User.User;
import com.rudy.jwtloginexample.domain.User.UserRepository;
import com.rudy.jwtloginexample.web.dto.UserRegisterRequestDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll() {
        return this.userRepository.findAll();
    }

    public User findById(Long id) {
        Optional<User> userOptional = this.userRepository.findById(id);
        return userOptional.orElse(null);
    }

    public User findByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }

    public User save(UserRegisterRequestDto user) {
        this.userRepository.save(user.toEntity());
        return user.toEntity();
    }

    public User findByPassword(String password) {
        return this.userRepository.findByPassword(password);
    }
}
