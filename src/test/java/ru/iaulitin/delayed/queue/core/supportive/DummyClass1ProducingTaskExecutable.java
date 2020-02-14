package ru.iaulitin.delayed.queue.core.supportive;

import lombok.extern.slf4j.Slf4j;
import ru.iaulitin.delayed.queue.core.executables.ITaskExecutable;

@Slf4j
public class DummyClass1ProducingTaskExecutable implements ITaskExecutable<DummyClass1> {

    @Override
    public void execute(DummyClass1 dummyObject) {
        log.info("Put an object {} to the queue", dummyObject);
    }
}
