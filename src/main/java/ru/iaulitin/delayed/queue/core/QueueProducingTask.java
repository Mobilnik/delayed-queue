package ru.iaulitin.delayed.queue.core;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.iaulitin.delayed.queue.utils.Action;

import java.util.concurrent.BlockingQueue;

@Slf4j
@RequiredArgsConstructor
public class QueueProducingTask<T> implements Runnable {


    private final BlockingQueue<T> queue;
    private final T queueObject;
    private final Action<T> action;


    public void run() {
        try {
            queue.put(queueObject);
            action.execute(queueObject);
        } catch (InterruptedException e) {
            log.error("Caught an interrupted exception: {}", e);
        }
    }
}
