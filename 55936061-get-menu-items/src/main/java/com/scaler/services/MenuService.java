package com.scaler.services;

import com.scaler.models.MenuItem;

import java.util.List;

public interface MenuService {

    List<MenuItem> getMenuItems(String itemType) throws Exception;
}
