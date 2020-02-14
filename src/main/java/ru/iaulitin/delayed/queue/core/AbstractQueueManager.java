package ru.iaulitin.delayed.queue.core;


import ru.iaulitin.delayed.queue.core.executables.ITaskExecutable;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;

public abstract class AbstractQueueManager<T extends DelayedTask> {


    //todo считывать настройку количества тредов из очереди
    private static final ExecutorService EXECUTOR_SERVICE = Executors.newFixedThreadPool(10);
    //fixme эту очередь надо делать синглтоном, возможно, доставать из другого класса!!!!
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
