package ru.iaulitin.delayed.queue.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.iaulitin.delayed.queue.dao.UserDao;
import ru.iaulitin.delayed.queue.entity.User;

@Service
@RequiredArgsConstructor
//fixme hack for opening transactions
public class UserDeactivationService {

    private final UserDao userDao;


    @Transactional
    public void deactivate(User user) {
        user.setActive(false);
        userDao.save(user);
    }
}
