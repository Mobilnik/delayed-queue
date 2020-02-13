package ru.iaulitin.delayed.queue.processor.core.impl.order.executables;

import lombok.extern.slf4j.Slf4j;
import ru.iaulitin.delayed.queue.entity.Order;
import ru.iaulitin.delayed.queue.entity.User;
import ru.iaulitin.delayed.queue.processor.core.executables.ITaskExecutable;

@Slf4j
public class OrderProducingTaskExecutable implements ITaskExecutable<Order> {


    @Override
    public void execute(Order order) {
        log.info("Put an order {} to queue", order);
    }
}
