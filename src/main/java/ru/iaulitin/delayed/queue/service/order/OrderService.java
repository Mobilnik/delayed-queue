package ru.iaulitin.delayed.queue.service.order;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.iaulitin.delayed.queue.dao.OrderDao;
import ru.iaulitin.delayed.queue.entity.Order;
import ru.iaulitin.delayed.queue.core.impl.order.OrderQueueManager;

import java.time.ZonedDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    private final OrderDao orderDao;
    private final OrderQueueManager orderQueueManager;


    @Transactional
    public void createOrder(long delayMillis) {
        Order order = new Order();
        order.setDeactivationTime(ZonedDateTime.now().plusSeconds(delayMillis / 1000));
        order.setActive(true);

        orderDao.save(order);

        orderQueueManager.handle(order);
    }
}
