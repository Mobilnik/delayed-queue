package ru.iaulitin.delayed.queue.processor.core;


import ru.iaulitin.delayed.queue.processor.core.executables.ITaskExecutable;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;

public abstract class AbstractQueueManager<T extends DelayedTask> {


    private static final ExecutorService EXECUTOR_SERVICE = Executors.newFixedThreadPool(10);
    private final BlockingQueue<T> queue = new PriorityBlockingQueue<>();


    public void handle(T t) {
        t.setProducingTaskExecutable(getProducingTaskExecutable());
        t.setConsumingTaskExecutable(getConsumingTaskExecutable());

        EXECUTOR_SERVICE.submit(new QueueProducingTask<>(queue, t));
        EXECUTOR_SERVICE.submit(new QueueConsumingTask<>(queue));
    }


    protected abstract ITaskExecutable<T> getProducingTaskExecutable();


    protected abstract ITaskExecutable<T> getConsumingTaskExecutable();
}