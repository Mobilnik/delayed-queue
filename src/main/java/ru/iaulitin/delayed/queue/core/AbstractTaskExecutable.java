package ru.iaulitin.delayed.queue.core;

import java.util.concurrent.Delayed;

public abstract class AbstractTaskExecutable<T extends Delayed> implements ITaskExecutable<T> {

    protected T queueObject;


    public T getQueueObject() {
        return queueObject;
    }


    public void setQueueObject(T queueObject) {
        this.queueObject = queueObject;
    }
}
