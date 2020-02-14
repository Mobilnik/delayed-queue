package ru.iaulitin.delayed.queue.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.iaulitin.delayed.queue.service.user.UserService;

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
