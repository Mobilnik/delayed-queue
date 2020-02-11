package ru.iaulitin.delayed.queue.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.iaulitin.delayed.queue.service.UserService;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @GetMapping("create")
    public void create(@RequestParam("delay") long delayMillis) {
        userService.createUser(delayMillis);
    }
}
