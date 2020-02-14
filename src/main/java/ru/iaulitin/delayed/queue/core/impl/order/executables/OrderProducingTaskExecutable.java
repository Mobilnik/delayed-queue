package ru.iaulitin.delayed.queue.core.impl.order.executables;

import lombok.extern.slf4j.Slf4j;
import ru.iaulitin.delayed.queue.core.executables.ITaskExecutable;
import ru.iaulitin.delayed.queue.entity.Order;

@Slf4j
public class OrderProducingTaskExecutable implements ITaskExecutable<Order> {


    @Override
    public void execute(Order order) {
        log.info("Put an order {} to queue", order);
    }
}
