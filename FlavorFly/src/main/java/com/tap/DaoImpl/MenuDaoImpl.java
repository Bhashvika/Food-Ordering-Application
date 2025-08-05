package com.tap.DaoImpl;

import com.tap.DB.ConnectDB;
import com.tap.Dao.MenuDao;
import com.tap.models.Menu;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MenuDaoImpl implements MenuDao {

    String insertQuery = "INSERT INTO menu (RestaurantId, ItemName, Description, Price, IsAvailable, Ratings, ImagePath) VALUES (?, ?, ?, ?, ?, ?, ?)";
    String selectByIdQuery = "SELECT * FROM menu WHERE MenuId=?";
    String selectByRestaurantQuery = "SELECT * FROM menu WHERE RestaurantId=?";
    String selectAllQuery = "SELECT * FROM menu";
    String updateQuery = "UPDATE menu SET RestaurantId=?, ItemName=?, Description=?, Price=?, IsAvailable=?, Ratings=?, ImagePath=? WHERE MenuId=?";
    String deleteQuery = "DELETE FROM menu WHERE MenuId=?";

    @Override
    public void insertMenu(Menu m) {
        try (Connection con = ConnectDB.getConnection();
             PreparedStatement pstmt = con.prepareStatement(insertQuery)) {

            pstmt.setInt(1, m.getRestaurantId());
            pstmt.setString(2, m.getItemName());
            pstmt.setString(3, m.getDescription());
            pstmt.setDouble(4, m.getPrice());
            pstmt.setBoolean(5, m.isAvailable());
            pstmt.setInt(6, m.getRatings());
            pstmt.setString(7, m.getImagePath());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Menu getMenuById(int menuId) {
        Menu menu = null;
        try (Connection con = ConnectDB.getConnection();
             PreparedStatement pstmt = con.prepareStatement(selectByIdQuery)) {

            pstmt.setInt(1, menuId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                menu = mapResultSetToMenu(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return menu;
    }

    @Override
    public List<Menu> getMenusByRestaurantId(int restaurantId) {
        List<Menu> list = new ArrayList<>();
        try (Connection con = ConnectDB.getConnection();
             PreparedStatement pstmt = con.prepareStatement(selectByRestaurantQuery)) {

            pstmt.setInt(1, restaurantId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                list.add(mapResultSetToMenu(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Menu> getAllMenus() {
        List<Menu> list = new ArrayList<>();
        try (Connection con = ConnectDB.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(selectAllQuery)) {

            while (rs.next()) {
                list.add(mapResultSetToMenu(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void updateMenu(Menu m) {
        try (Connection con = ConnectDB.getConnection();
             PreparedStatement pstmt = con.prepareStatement(updateQuery)) {

            pstmt.setInt(1, m.getRestaurantId());
            pstmt.setString(2, m.getItemName());
            pstmt.setString(3, m.getDescription());
            pstmt.setDouble(4, m.getPrice());
            pstmt.setBoolean(5, m.isAvailable());
            pstmt.setInt(6, m.getRatings());
            pstmt.setString(7, m.getImagePath());
            pstmt.setInt(8, m.getMenuId());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteMenu(int menuId) {
        try (Connection con = ConnectDB.getConnection();
             PreparedStatement pstmt = con.prepareStatement(deleteQuery)) {

            pstmt.setInt(1, menuId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Menu mapResultSetToMenu(ResultSet rs) throws SQLException {
        return new Menu(
                rs.getInt("MenuId"),
                rs.getInt("RestaurantId"),
                rs.getString("ItemName"),
                rs.getString("Description"),
                rs.getDouble("Price"),
                rs.getBoolean("IsAvailable"),
                rs.getInt("Ratings"),
                rs.getString("ImagePath")
        );
    }
}
