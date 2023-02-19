package com.solution.services;

import com.solution.entities.User;
import com.solution.entities.UserMeta;

public interface UserService {
    UserMeta getUserMeta(User user);
}
