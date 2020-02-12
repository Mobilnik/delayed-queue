package ru.iaulitin.delayed.queue.utils;

@FunctionalInterface
public interface Action<T> {

    void execute(T t);

}
