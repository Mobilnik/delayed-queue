package ru.iaulitin.delayed.queue.processor.core;

import java.util.concurrent.Delayed;

public interface ITaskExecutable<T extends Delayed> {
    T execute();
}
