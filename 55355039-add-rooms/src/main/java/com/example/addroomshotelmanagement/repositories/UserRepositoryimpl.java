package com.example.addroomshotelmanagement.repositories;

import java.util.HashMap;
import java.util.Map;

import com.example.addroomshotelmanagement.models.User;

public class UserRepositoryimpl implements UserRepository {

    Map<Long, User> userMap = new HashMap<>();

    @Override
    public User findById(long userId) {
        return userMap.get(userId);
    }

    @Override
    public User save(User user) {
        userMap.put(user.getId(), user);
        return user;
    }
    
}
