package ru.iaulitin.delayed.queue.core;

import java.util.concurrent.Delayed;

public interface ITaskExecutable<T extends Delayed> {
    T execute();
}
