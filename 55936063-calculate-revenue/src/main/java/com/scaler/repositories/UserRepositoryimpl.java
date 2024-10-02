package com.scaler.repositories;

import com.scaler.models.User;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class UserRepositoryimpl implements UserRepository{
    Map<Long,User> map = new HashMap<>();
    @Override
    public Optional<User> findById(long id) {
        return Optional.of(map.get(id));
    }

    @Override
    public User save(User user) {
        if (user.getId() == 0){
            user.setId(map.size()+1);
        }
        map.put(user.getId(), user);
        return user;
    }
}
