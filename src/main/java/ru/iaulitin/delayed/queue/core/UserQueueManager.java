package ru.iaulitin.delayed.queue.core;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.iaulitin.delayed.queue.entity.User;
import ru.iaulitin.delayed.queue.service.UserDeactivationService;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;


@Component
@Slf4j
@RequiredArgsConstructor
public class UserQueueManager extends AbstractQueueManager<User, UserProducingTaskExecutable, UserConsumingTaskExecutable> {

    private final BlockingQueue<User> queue = new PriorityBlockingQueue<>();
    private final UserDeactivationService userDeactivationService;


    @Override
    protected QueueProducingTask<User,UserProducingTaskExecutable> getProducingTask(User user) {
        return new QueueProducingTask<>(queue, user, new UserProducingTaskExecutable());
    }


    @Override
    protected QueueConsumingTask<User,UserConsumingTaskExecutable> getConsumingTask(User user) {
        return new QueueConsumingTask<>(queue, new UserConsumingTaskExecutable(userDeactivationService));
    }
}
