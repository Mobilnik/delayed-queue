package ru.iaulitin.delayed.queue.core;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
@Slf4j
public class QueueConsumingTask<T extends Delayed, S extends AbstractTaskExecutable<T>> implements Runnable {

    private final BlockingQueue<T> queue;
    private final S consumingTaskExecutable;


    @Override
    public void run() {
        try {
            T queueObject = queue.take();

            long sleepMillis = queueObject.getDelay(TimeUnit.MILLISECONDS);
            if (sleepMillis > 0) {
                Thread.sleep(sleepMillis);
            }

            consumingTaskExecutable.setQueueObject(queueObject);
            consumingTaskExecutable.execute();
        } catch (InterruptedException e) {
            log.error("Caught an interrupted exception: {}", e);
        }
    }

}
