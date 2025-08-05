package com.tap.Dao;

import java.util.List;
import com.tap.models.Restaurant;

public interface RestaurantDao {
     void insertRestaurant(Restaurant restaurant) ;
    Restaurant getRestaurantById(int restaurantId);
    List<Restaurant> getAllRestaurants();
    void updateRestaurant(Restaurant restaurant);
    void deleteRestaurant(int restaurantId);
}
