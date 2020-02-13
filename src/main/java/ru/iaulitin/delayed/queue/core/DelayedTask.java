package ru.iaulitin.delayed.queue.core;


import ru.iaulitin.delayed.queue.core.executables.ITaskExecutable;

import java.util.concurrent.Delayed;

public abstract class DelayedTask implements Delayed {

    private ITaskExecutable producingTaskExecutable;
    private ITaskExecutable consumingTaskExecutable;


    ITaskExecutable getProducingTaskExecutable() {
        return producingTaskExecutable;
    }

    void setProducingTaskExecutable(ITaskExecutable producingTaskExecutable) {
        this.producingTaskExecutable = producingTaskExecutable;
    }

    ITaskExecutable getConsumingTaskExecutable() {
        return consumingTaskExecutable;
    }

    void setConsumingTaskExecutable(ITaskExecutable consumingTaskExecutable) {
        this.consumingTaskExecutable = consumingTaskExecutable;
    }
}
