package com.tap.DaoImpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.tap.DB.ConnectDB;
import com.tap.Dao.RestaurantDao;
import com.tap.models.Restaurant;

public class RestaurantDaoImpl implements RestaurantDao {

    String insertQuery = "INSERT INTO restaurant (name, address, phonenumber, cuisineType, deliveryTime, adminUserId, rating, isActive, imagePath) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    String selectByIdQuery = "SELECT * FROM restaurant WHERE restaurantid = ?";
    String selectAllQuery = "SELECT * FROM restaurant";
    String updateQuery = "UPDATE restaurant SET name=?, address=?, phonenumber=?, cuisineType=?, deliveryTime=?, adminUserId=?, rating=?, isActive=?, imagePath=? WHERE restaurantid=?";
    String deleteQuery = "DELETE FROM restaurant WHERE restaurantid = ?";

    @Override
    public void insertRestaurant(Restaurant r) {
        try (Connection con = ConnectDB.getConnection();
             PreparedStatement pstmt = con.prepareStatement(insertQuery)) {

            pstmt.setString(1, r.getName());
            pstmt.setString(2, r.getAddress());
            pstmt.setString(3, r.getPhonenumber());
            pstmt.setString(4, r.getCuisineType());
            pstmt.setTime(5, r.getDeliveryTime());
            pstmt.setInt(6, r.getAdminUserId());
            pstmt.setInt(7, r.getRating());
            pstmt.setBoolean(8, r.isActive());
            pstmt.setString(9, r.getImagePath());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Restaurant getRestaurantById(int restaurantId) {
        Restaurant r = null;
        try (Connection con = ConnectDB.getConnection();
             PreparedStatement pstmt = con.prepareStatement(selectByIdQuery)) {

            pstmt.setInt(1, restaurantId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                r = mapResultSetToRestaurant(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return r;
    }

    @Override
    public List<Restaurant> getAllRestaurants() {
        List<Restaurant> list = new ArrayList<>();
        try (Connection con = ConnectDB.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(selectAllQuery)) {

            while (rs.next()) {
                Restaurant r = mapResultSetToRestaurant(rs);
                list.add(r);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void updateRestaurant(Restaurant r) {
        try (Connection con = ConnectDB.getConnection();
             PreparedStatement pstmt = con.prepareStatement(updateQuery)) {

            pstmt.setString(1, r.getName());
            pstmt.setString(2, r.getAddress());
            pstmt.setString(3, r.getPhonenumber());
            pstmt.setString(4, r.getCuisineType());
            pstmt.setTime(5, r.getDeliveryTime());
            pstmt.setInt(6, r.getAdminUserId());
            pstmt.setInt(7, r.getRating());
            pstmt.setBoolean(8, r.isActive());
            pstmt.setString(9, r.getImagePath());
            pstmt.setInt(10, r.getRestaurantid());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteRestaurant(int restaurantId) {
        try (Connection con = ConnectDB.getConnection();
             PreparedStatement pstmt = con.prepareStatement(deleteQuery)) {

            pstmt.setInt(1, restaurantId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Restaurant mapResultSetToRestaurant(ResultSet rs) throws SQLException {
        return new Restaurant(
                rs.getInt("restaurantid"),
                rs.getString("name"),
                rs.getString("address"),
                rs.getString("phonenumber"),
                rs.getString("cuisineType"),
                rs.getTime("deliveryTime"),
                rs.getInt("adminUserId"),
                rs.getInt("rating"),
                rs.getBoolean("isActive"),
                rs.getString("imagePath")
        );
    }
}
