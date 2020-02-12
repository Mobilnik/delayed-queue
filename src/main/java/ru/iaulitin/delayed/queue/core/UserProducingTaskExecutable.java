package ru.iaulitin.delayed.queue.core;

import lombok.extern.slf4j.Slf4j;
import ru.iaulitin.delayed.queue.entity.User;

@Slf4j
public class UserProducingTaskExecutable extends AbstractTaskExecutable<User> {


    @Override
    public User execute() {
        log.info("Put a user {} to queue", queueObject);
        return queueObject;
    }
}
