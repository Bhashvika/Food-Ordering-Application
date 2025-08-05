package com.tap.DaoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.tap.DB.ConnectDB;
import com.tap.Dao.UserDao;
import com.tap.models.UserModel;

public class UserDaoImpl implements UserDao {

    String SelectQuery = "select * from `user`";
    String InsertQuery = "insert into `user`(`name`,`username`,`password`,`email`,`phone`,`address`,`role`) Values(?,?,?,?,?,?,?)";
    String selectQuery1 = "select * from `user` where `userid`=?";
    String updateQuery = "Update `user` set `name`=?, `password`=?, `phone`=?, `address`=?, `role`=? where `userid`=?";
    String deleteQuery = "Delete from `user` where `userid`=?";

    @Override
    public List<UserModel> getAllUsers() {
        Connection con = ConnectDB.getConnection();
        List<UserModel> list = new ArrayList<>();
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(SelectQuery);
            while (rs.next()) {
                int userid = rs.getInt(1);
                String name = rs.getString(2);
                String username = rs.getString(3);
                String password = rs.getString(4);
                String email = rs.getString(5);
                String phone = rs.getString(6);
                String address = rs.getString(7);
                String role = rs.getString(8);
                Date createdDate = rs.getDate(9);
                Date lastLoginDate = rs.getDate(10);
                UserModel u = new UserModel(userid, name, username, password, email, phone, address, role, createdDate, lastLoginDate);
                list.add(u);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public UserModel getUser(int userid) {
        Connection con = ConnectDB.getConnection();
        UserModel u = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            pstmt = con.prepareStatement(selectQuery1);
            pstmt.setInt(1, userid);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                String username = rs.getString(3);
                String password = rs.getString(4);
                String email = rs.getString(5);
                String phone = rs.getString(6);
                String address = rs.getString(7);
                String role = rs.getString(8);
                Date createdDate = rs.getDate(9);
                Date lastLoginDate = rs.getDate(10);

                u = new UserModel(id, name, username, password, email, phone, address, role, createdDate, lastLoginDate);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return u;
    }

    @Override
    public void insertUser(UserModel u) {
        Connection con = ConnectDB.getConnection();
        PreparedStatement pstmt = null;
        try {
            pstmt = con.prepareStatement(InsertQuery);

            pstmt.setString(1, u.getName());
            pstmt.setString(2, u.getUsername());
            pstmt.setString(3, u.getPassword());
            pstmt.setString(4, u.getEmail());
            pstmt.setString(5, u.getPhone());
            pstmt.setString(6, u.getAddress());
            pstmt.setString(7, u.getRole());
            int res = pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void updateUser(int userid, UserModel u) {
        Connection con = ConnectDB.getConnection();
        PreparedStatement pstmt = null;
        try {
            pstmt = con.prepareStatement(updateQuery);
            pstmt.setString(1, u.getName());
            pstmt.setString(2, u.getPassword());
            pstmt.setString(3, u.getPhone());
            pstmt.setString(4, u.getAddress());
            pstmt.setString(5, u.getRole());
            pstmt.setInt(6, userid);
            int res = pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void deleteUser(int userid) {
        Connection con = ConnectDB.getConnection();
        PreparedStatement pstmt = null;
        try {
            pstmt = con.prepareStatement(deleteQuery);
            pstmt.setInt(1, userid);
            int res = pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
