package ru.iaulitin.delayed.queue.core.supportive;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import ru.iaulitin.delayed.queue.core.DelayedTask;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

@Data
@EqualsAndHashCode(callSuper = false)
@ToString
public class DummyClass1 extends DelayedTask {


    private long delayMillis;
    private boolean active;


    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(delayMillis, TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed dummyObject1) {
        return Long.compare(delayMillis, dummyObject1.getDelay(TimeUnit.MILLISECONDS));
    }
}