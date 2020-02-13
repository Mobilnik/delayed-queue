package ru.iaulitin.delayed.queue.core;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.BlockingQueue;

@Slf4j
@RequiredArgsConstructor
public final class QueueProducingTask<T extends DelayedTask> implements Runnable {


    private final BlockingQueue<T> queue;
    private final T queueObject;


    public void run() {
        try {
            queue.put(queueObject);
            queueObject.getProducingTaskExecutable().execute(queueObject);
        } catch (InterruptedException e) {
            log.error("Caught an interrupted exception: {}", e);
        }
    }
}
