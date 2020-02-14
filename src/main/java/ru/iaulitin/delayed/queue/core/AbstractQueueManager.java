package ru.iaulitin.delayed.queue.core;


import ru.iaulitin.delayed.queue.core.executables.ITaskExecutable;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public abstract class AbstractQueueManager<T extends DelayedTask> {

    //todo считывать настройку количества тредов из очереди
    private static final ExecutorService EXECUTOR_SERVICE = Executors.newFixedThreadPool(10);
    private final BlockingQueue<DelayedTask> queue = SingletonQueueHolder.getQueue();


    public void handle(T t) {
        t.setProducingTaskExecutable(getProducingTaskExecutable());
        t.setConsumingTaskExecutable(getConsumingTaskExecutable());

        EXECUTOR_SERVICE.submit(new QueueProducingTask<>(queue, t));
        EXECUTOR_SERVICE.submit(new QueueConsumingTask<>(queue));
    }


    protected abstract ITaskExecutable<T> getProducingTaskExecutable();


    protected abstract ITaskExecutable<T> getConsumingTaskExecutable();
}
