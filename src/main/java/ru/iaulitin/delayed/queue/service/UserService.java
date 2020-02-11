package ru.iaulitin.delayed.queue.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.iaulitin.delayed.queue.dao.UserDao;
import ru.iaulitin.delayed.queue.entity.User;

import java.time.ZonedDateTime;

@Service
@RequiredArgsConstructor
public class UserService {

    private static int usersCount = 0;


    private final UserDao userDao;


    public void createUser(long delayMillis) {
        User user = new User();
        user.setName("User " + ++usersCount);
        user.setDeactivationTime(ZonedDateTime.now().plusSeconds(delayMillis / 1000));

        userDao.save(user);
    }
}
