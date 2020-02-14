package ru.iaulitin.delayed.queue.core.supportive;

import lombok.extern.slf4j.Slf4j;
import ru.iaulitin.delayed.queue.core.executables.ITaskExecutable;

@Slf4j
public class DummyClass1ConsumingTaskExecutable implements ITaskExecutable<DummyClass1> {

    @Override
    public void execute(DummyClass1 dummyObject) {
        dummyObject.setActive(false);
        log.info("Deactivated an object {}", dummyObject);
    }
}
