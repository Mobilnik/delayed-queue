package ru.iaulitin.delayed.queue.core.impl.user.executables;

import lombok.extern.slf4j.Slf4j;
import ru.iaulitin.delayed.queue.core.executables.ITaskExecutable;
import ru.iaulitin.delayed.queue.entity.User;

@Slf4j
public class UserProducingTaskExecutable implements ITaskExecutable<User> {


    @Override
    public void execute(User user) {
        log.info("Put a user {} to queue", user);
    }
}
