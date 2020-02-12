package ru.iaulitin.delayed.queue.core;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.iaulitin.delayed.queue.entity.User;
import ru.iaulitin.delayed.queue.service.UserDeactivationService;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;


@Component
@Slf4j
@RequiredArgsConstructor
public class UserQueueManager {

    private final BlockingQueue<User> queue = new PriorityBlockingQueue<>();
    private final ExecutorService executorService = Executors.newFixedThreadPool(2);

    private final UserDeactivationService userDeactivationService;


    public void handle(User user) {
        Runnable producingTask = new QueueProducingTask<>(
                queue, user, (aUser) -> log.info("Put a user {} to queue", aUser));
        executorService.submit(producingTask);

        Runnable consumingTask = new UserQueueConsumingTask(queue, userDeactivationService);
        executorService.submit(consumingTask);
    }
}
