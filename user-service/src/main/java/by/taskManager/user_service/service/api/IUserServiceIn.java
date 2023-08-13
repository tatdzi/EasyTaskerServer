package by.taskManager.user_service.service.api;

import java.util.List;
import java.util.UUID;


public interface IUserServiceIn {
    void check(List<UUID> items);
    void checkManager(UUID manager);
}
