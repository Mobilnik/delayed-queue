package ru.iaulitin.delayed.queue.core;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.iaulitin.delayed.queue.entity.User;

import java.util.concurrent.BlockingQueue;

@RequiredArgsConstructor
@Slf4j
public class UserQueueProducingTask implements Runnable {


    private final BlockingQueue<User> queue;
    private final User user;


    @Override
    public void run() {
        try {
            queue.put(user);
            log.info("Put a user {} to queue", user);
        } catch (InterruptedException e) {
            log.error("Caught an interrupted exception: {}", e);
        }
    }

}
