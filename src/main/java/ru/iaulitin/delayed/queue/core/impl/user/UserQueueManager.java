package ru.iaulitin.delayed.queue.core.impl.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.iaulitin.delayed.queue.core.AbstractQueueManager;
import ru.iaulitin.delayed.queue.core.executables.ITaskExecutable;
import ru.iaulitin.delayed.queue.core.impl.user.executables.UserConsumingTaskExecutable;
import ru.iaulitin.delayed.queue.core.impl.user.executables.UserProducingTaskExecutable;
import ru.iaulitin.delayed.queue.entity.User;
import ru.iaulitin.delayed.queue.service.user.UserDeactivationService;


@Component
@Slf4j
@RequiredArgsConstructor
public class UserQueueManager extends AbstractQueueManager<User> {

    private final UserDeactivationService userDeactivationService;

    @Override
    protected ITaskExecutable<User> getProducingTaskExecutable() {
        return new UserProducingTaskExecutable();
    }


    @Override
    protected ITaskExecutable<User> getConsumingTaskExecutable() {
        return new UserConsumingTaskExecutable(userDeactivationService);
    }
}
