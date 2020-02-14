package ru.iaulitin.delayed.queue.core.supportive;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.iaulitin.delayed.queue.core.AbstractQueueManager;
import ru.iaulitin.delayed.queue.core.executables.ITaskExecutable;

@Component
@Slf4j
@RequiredArgsConstructor
public class DummyClass1QueueManager extends AbstractQueueManager<DummyClass1> {


    @Override
    protected ITaskExecutable<DummyClass1> getProducingTaskExecutable() {
        return new DummyClass1ProducingTaskExecutable();
    }


    @Override
    protected ITaskExecutable<DummyClass1> getConsumingTaskExecutable() {
        return new DummyClass1ConsumingTaskExecutable();
    }
}