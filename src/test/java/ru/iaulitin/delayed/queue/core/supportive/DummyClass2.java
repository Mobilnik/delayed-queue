package ru.iaulitin.delayed.queue.core.supportive;

import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.iaulitin.delayed.queue.core.DelayedTask;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

@Data
@EqualsAndHashCode(callSuper = false)
public class DummyClass2 extends DelayedTask {


    private long delayMillis;
    private boolean active;


    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(delayMillis, TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed dummyObject2) {
        return Long.compare(delayMillis, dummyObject2.getDelay(TimeUnit.MILLISECONDS));
    }
}