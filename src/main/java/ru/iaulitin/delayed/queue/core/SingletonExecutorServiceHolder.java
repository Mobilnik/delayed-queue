package ru.iaulitin.delayed.queue.core;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class SingletonExecutorServiceHolder {

    private static volatile ExecutorService executorService;
    private static int threadCount;


    @Value("${queue.manager.threads.count}")
    public void initExecutorService(int queueManagerThreadsCount) {
        threadCount = queueManagerThreadsCount;
    }


    static ExecutorService getExecutorService() {
        ExecutorService localExecutorService = executorService;
        if (executorService == null) {
            synchronized (SingletonQueueHolder.class) {
                localExecutorService = executorService;
                if (localExecutorService == null) {
                    executorService = localExecutorService = Executors.newFixedThreadPool(threadCount);
                }
            }
        }
        return localExecutorService;
    }
}
