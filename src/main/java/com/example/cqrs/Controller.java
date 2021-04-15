package com.example.cqrs;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class Controller {


    private final UserRepository userRepository;

    public Controller(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable String id) {
        return userRepository.findById(id)
                             .orElseThrow(() -> new RuntimeException("user not found"));
    }

    @PostMapping
    @ResponseStatus(code =  HttpStatus.CREATED)
    public User createUser(@RequestBody User user) {
        userRepository.save(user);
        return userRepository.findById(user.getId()).get();
    }
}
