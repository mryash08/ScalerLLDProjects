package com.example.bookrooms.repositories;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.example.bookrooms.models.User;

public class UserRepositoryImpl implements UserRepository{
    Map<Long, User> map = new HashMap<>();

    @Override
    public Optional<User> findById(long id) {
        return Optional.ofNullable(map.get(id));
    }

    @Override
    public User save(User user) {
        map.put(user.getId(), user);
        return user;
    }
    
}
