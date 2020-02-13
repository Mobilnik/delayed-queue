package ru.iaulitin.delayed.queue.service.order;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.iaulitin.delayed.queue.dao.OrderDao;
import ru.iaulitin.delayed.queue.dao.UserDao;
import ru.iaulitin.delayed.queue.entity.Order;
import ru.iaulitin.delayed.queue.entity.User;

@Service
@RequiredArgsConstructor
public class OrderDeactivationService {

    private final OrderDao orderDao;


    @Transactional
    public void deactivate(Order user) {
        user.setActive(false);
        orderDao.save(user);
    }
}
