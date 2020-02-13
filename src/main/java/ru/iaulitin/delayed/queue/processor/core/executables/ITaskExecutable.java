package ru.iaulitin.delayed.queue.processor.core.executables;


import ru.iaulitin.delayed.queue.processor.core.DelayedTask;

public interface ITaskExecutable<T extends DelayedTask> {
    void execute(T t);
}
