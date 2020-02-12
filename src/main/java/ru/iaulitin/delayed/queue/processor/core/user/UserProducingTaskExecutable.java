package ru.iaulitin.delayed.queue.processor.core.user;

import lombok.extern.slf4j.Slf4j;
import ru.iaulitin.delayed.queue.entity.User;
import ru.iaulitin.delayed.queue.processor.core.AbstractTaskExecutable;

@Slf4j
public class UserProducingTaskExecutable extends AbstractTaskExecutable<User> {


    @Override
    public User execute() {
        log.info("Put a user {} to queue", queueObject);
        return queueObject;
    }
}
