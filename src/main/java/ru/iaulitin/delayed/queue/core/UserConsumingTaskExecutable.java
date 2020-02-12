package ru.iaulitin.delayed.queue.core;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.iaulitin.delayed.queue.entity.User;
import ru.iaulitin.delayed.queue.service.UserDeactivationService;

@RequiredArgsConstructor
@Slf4j
public class UserConsumingTaskExecutable extends AbstractTaskExecutable<User> {

    private final UserDeactivationService userDeactivationService;


    @Override
    public User execute() {
        userDeactivationService.deactivate(queueObject);
        log.info("Deactivated a user {}", queueObject);
        return queueObject;
    }
}
