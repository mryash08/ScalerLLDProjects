package com.scaler.repositories;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.scaler.models.User;

public class UserRepoImpl implements UserRepository {

    private Map<Long, User> users;

    public UserRepoImpl() {
        this.users = new HashMap<>();
    }

    @Override
    public User save(User user) {
        if(user.getId() == 0){
            user.setId(users.size() + 1);
        }
        users.put(user.getId(), user);
        return user;
    }

    @Override
    public Optional<User> findById(long id) {
        return Optional.ofNullable(users.get(id));
    }
    
}
