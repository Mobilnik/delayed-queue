package ru.iaulitin.delayed.queue.processor.core;


import ru.iaulitin.delayed.queue.processor.core.executables.ITaskExecutable;

import java.util.concurrent.Delayed;

public abstract class DelayedTask implements Delayed {

    private ITaskExecutable producingTaskExecutable;
    private ITaskExecutable consumingTaskExecutable;


    public ITaskExecutable getProducingTaskExecutable() {
        return producingTaskExecutable;
    }

    public void setProducingTaskExecutable(ITaskExecutable producingTaskExecutable) {
        this.producingTaskExecutable = producingTaskExecutable;
    }

    public ITaskExecutable getConsumingTaskExecutable() {
        return consumingTaskExecutable;
    }

    public void setConsumingTaskExecutable(ITaskExecutable consumingTaskExecutable) {
        this.consumingTaskExecutable = consumingTaskExecutable;
    }
}
