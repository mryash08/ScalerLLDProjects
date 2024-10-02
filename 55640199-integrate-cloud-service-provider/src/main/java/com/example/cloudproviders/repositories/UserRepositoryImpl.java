package com.example.cloudproviders.repositories;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.example.cloudproviders.models.User;

public class UserRepositoryImpl implements UserRepository {

    Map<Long, User> userMap = new HashMap<>();

    @Override
    public User save(User user) {
        userMap.put(user.getId(), user);
        return user;
    }

    @Override
    public Optional<User> findUserById(long userId) {
        return Optional.ofNullable(userMap.get(userId));
    }
    
}
