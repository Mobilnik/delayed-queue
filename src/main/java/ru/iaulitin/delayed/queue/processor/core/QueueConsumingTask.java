package ru.iaulitin.delayed.queue.processor.core;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
@Slf4j
public final class QueueConsumingTask<T extends DelayedTask> implements Runnable {

    private final BlockingQueue<T> queue;


    @Override
    public void run() {
        try {
            T queueObject = queue.take();

            long sleepMillis = queueObject.getDelay(TimeUnit.MILLISECONDS);
            if (sleepMillis > 0) {
                Thread.sleep(sleepMillis);
            }

            queueObject.getConsumingTaskExecutable().execute(queueObject);
        } catch (InterruptedException e) {
            log.error("Caught an interrupted exception: {}", e);
        }
    }

}
