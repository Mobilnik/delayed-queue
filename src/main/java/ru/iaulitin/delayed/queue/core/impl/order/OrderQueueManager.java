package ru.iaulitin.delayed.queue.core.impl.order;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.iaulitin.delayed.queue.entity.Order;
import ru.iaulitin.delayed.queue.core.AbstractQueueManager;
import ru.iaulitin.delayed.queue.core.executables.ITaskExecutable;
import ru.iaulitin.delayed.queue.core.impl.order.executables.OrderConsumingTaskExecutable;
import ru.iaulitin.delayed.queue.core.impl.order.executables.OrderProducingTaskExecutable;
import ru.iaulitin.delayed.queue.service.order.OrderDeactivationService;


@Component
@Slf4j
@RequiredArgsConstructor
public class OrderQueueManager extends AbstractQueueManager<Order> {

    private final OrderDeactivationService orderDeactivationService;

    @Override
    protected ITaskExecutable<Order> getProducingTaskExecutable() {
        return new OrderProducingTaskExecutable();
    }


    @Override
    protected ITaskExecutable<Order> getConsumingTaskExecutable() {
        return new OrderConsumingTaskExecutable(orderDeactivationService);
    }
}
