package ru.iaulitin.delayed.queue.core;


import java.util.concurrent.Delayed;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public abstract class AbstractQueueManager<T extends Delayed, S extends AbstractTaskExecutable<T>, V extends AbstractTaskExecutable<T>> {

    protected static final ExecutorService EXECUTOR_SERVICE = Executors.newFixedThreadPool(10);


    public void handle(T t) {
        EXECUTOR_SERVICE.submit(getProducingTask(t));
        EXECUTOR_SERVICE.submit(getConsumingTask(t));
    }


    protected abstract QueueProducingTask<T, S> getProducingTask(T t);
    protected abstract QueueConsumingTask<T, V> getConsumingTask(T t);
}
