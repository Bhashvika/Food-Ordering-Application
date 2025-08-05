package com.tap.Dao;

import com.tap.models.Menu;
import java.util.List;

public interface MenuDao {
    void insertMenu(Menu menu);
    Menu getMenuById(int menuId);
    List<Menu> getMenusByRestaurantId(int restaurantId);
    List<Menu> getAllMenus();
    void updateMenu(Menu menu);
    void deleteMenu(int menuId);
}
