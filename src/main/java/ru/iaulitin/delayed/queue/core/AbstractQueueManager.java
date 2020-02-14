package ru.iaulitin.delayed.queue.core;


import org.springframework.context.annotation.Lazy;
import ru.iaulitin.delayed.queue.core.executables.ITaskExecutable;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicBoolean;

@Lazy
public abstract class AbstractQueueManager<T extends DelayedTask> {

    private static AtomicBoolean isInit = new AtomicBoolean(false);
    private static BlockingQueue<DelayedTask> queue;
    private static ExecutorService executorService;


    public void handle(T t) {
        if (!isInit.get()) {
            init();
        }

        if (t == null) {
            return;
        }

        t.setProducingTaskExecutable(getProducingTaskExecutable());
        t.setConsumingTaskExecutable(getConsumingTaskExecutable());

        executorService.submit(new QueueProducingTask<>(queue, t));
        executorService.submit(new QueueConsumingTask<>(queue));
    }


    private void init() {
        queue = SingletonQueueHolder.getQueue();
        executorService = SingletonExecutorServiceHolder.getExecutorService();
        isInit.set(true);
    }


    protected abstract ITaskExecutable<T> getProducingTaskExecutable();


    protected abstract ITaskExecutable<T> getConsumingTaskExecutable();
}
