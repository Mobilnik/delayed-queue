package ru.iaulitin.delayed.queue.service.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.iaulitin.delayed.queue.dao.UserDao;
import ru.iaulitin.delayed.queue.entity.User;
import ru.iaulitin.delayed.queue.core.impl.user.UserQueueManager;

import java.time.ZonedDateTime;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private static final AtomicInteger usersCount = new AtomicInteger(0);


    private final UserDao userDao;
    private final UserQueueManager userQueueManager;


    @Transactional
    public void createUser(long delayMillis) {
        User user = new User();
        user.setName("User " + usersCount.incrementAndGet());
        user.setDeactivationTime(ZonedDateTime.now().plusSeconds(delayMillis / 1000));
        user.setActive(true);

        userDao.save(user);

        userQueueManager.handle(user);
    }
}
