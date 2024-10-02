package com.scaler.repositories;

import com.scaler.models.User;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

public class UserRepoImpl implements UserRepository{

    Map<Long,User> userDatabase;
    private static long id = 0;

    public UserRepoImpl() {
        this.userDatabase = new HashMap<>();
    }

    @Override
    public Optional<User> findById(long id) {
        return Optional.of(userDatabase.get(id));
    }

    @Override
    public User save(User user) {
        if(user.getId() == 0){
            user.setId(++id);
        }
        userDatabase.put(user.getId(),user);
        return user;
    }
}
