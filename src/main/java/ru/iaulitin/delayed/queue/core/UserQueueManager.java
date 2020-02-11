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

    private final UserDeactivationService userDeactivationService;


    private final BlockingQueue<User> queue = new PriorityBlockingQueue<>();
    ExecutorService executorService = Executors.newFixedThreadPool(10);


    public void handle(User user) {
        Runnable producingTask = new UserQueueProducingTask(queue, user);
        Runnable consumingTask = new UserQueueConsumingTask(queue, userDeactivationService);

        executorService.submit(producingTask);
        executorService.submit(consumingTask);
    }
}
