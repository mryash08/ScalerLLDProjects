package com.scaler.repositories;

import com.scaler.models.MenuItem;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class MenuItemRepositoryImpl implements MenuItemRepository{
    private Map<Long, MenuItem> menuMap;
    private  long id = 0;

    public MenuItemRepositoryImpl() {
        this.menuMap = new HashMap<>();
    }
    @Override
    public MenuItem add(MenuItem menuItem) {
        if (menuItem.getId() == 0){
            menuItem.setId(++id);
        }
        menuMap.put(menuItem.getId(), menuItem);
        return menuItem;
    }

    @Override
    public Optional<MenuItem> findById(long id) {
        return menuMap.values().stream().filter(menuMap -> menuMap.getId() == id).findFirst();
    }
}
