package ru.iaulitin.delayed.queue.core;


import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

public final class SingletonQueueHolder {

    private static volatile BlockingQueue<DelayedTask> queue;


    public static BlockingQueue<DelayedTask> getQueue() {
        BlockingQueue<DelayedTask> localQueue = queue;
        if (queue == null) {
            synchronized (SingletonQueueHolder.class) {
                localQueue = queue;
                if (localQueue == null) {
                    queue = localQueue = new PriorityBlockingQueue<>();
                }
            }
        }
        return localQueue;
    }
}
