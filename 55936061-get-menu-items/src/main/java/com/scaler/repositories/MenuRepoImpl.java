package com.scaler.repositories;

import com.scaler.models.DietaryRequirement;
import com.scaler.models.MenuItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MenuRepoImpl implements MenuRepository {

    Map<DietaryRequirement,List<MenuItem>> database;
    List<MenuItem> allMenu;
    private static long id = 0;

    public MenuRepoImpl(){
        database = new HashMap<>();
        allMenu = new ArrayList<>();
    }
    @Override
    public MenuItem add(MenuItem menuItem) {
        if(menuItem.getId() == 0){
            menuItem.setId(id++);
        }
        if(!database.containsKey(menuItem.getDietaryRequirement())){
            database.put(menuItem.getDietaryRequirement(),new ArrayList<>());
        }
        database.get(menuItem.getDietaryRequirement()).add(menuItem);
        allMenu.add(menuItem);
        return menuItem;
    }

    @Override
    public List<MenuItem> getAll() {
        return allMenu;
    }

    @Override
    public List<MenuItem> getByDietaryRequirement(DietaryRequirement dietaryRequirement) {
        return database.get(dietaryRequirement);
    }

    @Override
    public MenuItem save(MenuItem menuItem) {
        if(menuItem.getId() == 0){
            menuItem.setId(id++);
        }
        if(!database.containsKey(menuItem.getDietaryRequirement())){
            database.put(menuItem.getDietaryRequirement(),new ArrayList<>());
        }
        database.get(menuItem.getDietaryRequirement()).add(menuItem);
        allMenu.add(menuItem);
        return menuItem;
    }
}
