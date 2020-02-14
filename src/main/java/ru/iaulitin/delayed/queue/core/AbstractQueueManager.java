package ru.iaulitin.delayed.queue.core;


import org.springframework.beans.factory.annotation.Value;
import ru.iaulitin.delayed.queue.core.executables.ITaskExecutable;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public abstract class AbstractQueueManager<T extends DelayedTask> {

    private static BlockingQueue<DelayedTask> queue = SingletonQueueHolder.getQueue();
    private static ExecutorService executorService;


    @Value("${queue.manager.threads.count}")
    public void initExecutorService(int queueManagerThreadsCount) {
        executorService = Executors.newFixedThreadPool(queueManagerThreadsCount);
    }


    public void handle(T t) {
        t.setProducingTaskExecutable(getProducingTaskExecutable());
        t.setConsumingTaskExecutable(getConsumingTaskExecutable());

        executorService.submit(new QueueProducingTask<>(queue, t));
        executorService.submit(new QueueConsumingTask<>(queue));
    }


    protected abstract ITaskExecutable<T> getProducingTaskExecutable();


    protected abstract ITaskExecutable<T> getConsumingTaskExecutable();
}
