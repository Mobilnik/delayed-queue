package ru.iaulitin.delayed.queue.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.iaulitin.delayed.queue.entity.User;

@Repository
public interface UserDao extends CrudRepository<User, Long> {
}
