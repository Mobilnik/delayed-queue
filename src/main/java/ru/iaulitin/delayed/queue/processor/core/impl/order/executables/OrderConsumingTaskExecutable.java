package ru.iaulitin.delayed.queue.processor.core.impl.order.executables;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.iaulitin.delayed.queue.entity.Order;
import ru.iaulitin.delayed.queue.processor.core.executables.ITaskExecutable;
import ru.iaulitin.delayed.queue.service.order.OrderDeactivationService;

@RequiredArgsConstructor
@Slf4j
public class OrderConsumingTaskExecutable implements ITaskExecutable<Order> {

    private final OrderDeactivationService orderDeactivationService;


    @Override
    public void execute(Order order) {
        orderDeactivationService.deactivate(order);
        log.info("Deactivated an order {}", order);
    }
}
