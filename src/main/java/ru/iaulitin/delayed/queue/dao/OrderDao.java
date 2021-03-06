package ru.iaulitin.delayed.queue.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.iaulitin.delayed.queue.entity.Order;

@Repository
public interface OrderDao extends CrudRepository<Order, Long> {
}
