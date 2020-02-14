package ru.iaulitin.delayed.queue.core.impl.user.executables;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.iaulitin.delayed.queue.core.executables.ITaskExecutable;
import ru.iaulitin.delayed.queue.entity.User;
import ru.iaulitin.delayed.queue.service.user.UserDeactivationService;

@RequiredArgsConstructor
@Slf4j
public class UserConsumingTaskExecutable implements ITaskExecutable<User> {

    private final UserDeactivationService userDeactivationService;


    @Override
    public void execute(User user) {
        userDeactivationService.deactivate(user);
        log.info("Deactivated a user {}", user);
    }
}
