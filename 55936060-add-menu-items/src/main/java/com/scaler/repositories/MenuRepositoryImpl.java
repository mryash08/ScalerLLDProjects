package com.scaler.repositories;

import com.scaler.models.DietaryRequirement;
import com.scaler.models.MenuItem;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class MenuRepositoryImpl implements MenuRepository{
    HashMap<Locale,MenuItem> database;
    private static long id = 0;
    public MenuRepositoryImpl() {
        database = new HashMap<>();
    }

    @Override
    public MenuItem add(MenuItem menuItem) {
        if(menuItem.getId() == 0){
            menuItem.setId(++id);
        }
        return menuItem;
    }

    @Override
    public List<MenuItem> getAll() {
        return database.values().stream().toList();
    }

    @Override
    public List<MenuItem> getByDietaryRequirement(DietaryRequirement dietaryRequirement) {
        return database.values().stream().filter(menuItem -> menuItem.getDietaryRequirement() == dietaryRequirement).toList();
    }
}
