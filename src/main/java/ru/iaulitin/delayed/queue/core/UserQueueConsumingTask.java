package ru.iaulitin.delayed.queue.core;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.iaulitin.delayed.queue.entity.User;
import ru.iaulitin.delayed.queue.service.UserDeactivationService;

import java.util.concurrent.BlockingQueue;

@RequiredArgsConstructor
@Slf4j
public class UserQueueConsumingTask implements Runnable {

    private final BlockingQueue<User> queue;
    private final UserDeactivationService userDeactivationService;


    @Override
    public void run() {
        try {
            User user = queue.take();
            userDeactivationService.deactivate(user);
            log.info("Deactivated a user {}", user);
        } catch (InterruptedException e) {
            log.error("Caught an interrupted exception: {}", e);
        }
    }

}
