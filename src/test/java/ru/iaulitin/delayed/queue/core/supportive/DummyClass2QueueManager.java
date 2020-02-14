package ru.iaulitin.delayed.queue.core.supportive;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.iaulitin.delayed.queue.core.AbstractQueueManager;
import ru.iaulitin.delayed.queue.core.executables.ITaskExecutable;

@Component
@Slf4j
@RequiredArgsConstructor
public class DummyClass2QueueManager extends AbstractQueueManager<DummyClass2> {


    @Override
    protected ITaskExecutable<DummyClass2> getProducingTaskExecutable() {
        return new DummyClass2ProducingTaskExecutable();
    }


    @Override
    protected ITaskExecutable<DummyClass2> getConsumingTaskExecutable() {
        return new DummyClass2ConsumingTaskExecutable();
    }
}