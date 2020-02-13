package ru.iaulitin.delayed.queue.core.executables;


import ru.iaulitin.delayed.queue.core.DelayedTask;

public interface ITaskExecutable<T extends DelayedTask> {
    void execute(T t);
}
