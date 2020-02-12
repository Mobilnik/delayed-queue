package ru.iaulitin.delayed.queue.processor.core;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Delayed;

@Slf4j
@RequiredArgsConstructor
public class QueueProducingTask<T extends Delayed, S extends AbstractTaskExecutable<T>> implements Runnable {


    private final BlockingQueue<T> queue;
    private final T queueObject;
    private final S producingTaskExecutable;


    public void run() {
        try {
            queue.put(queueObject);

            producingTaskExecutable.setQueueObject(queueObject);
            producingTaskExecutable.execute();
        } catch (InterruptedException e) {
            log.error("Caught an interrupted exception: {}", e);
        }
    }
}
