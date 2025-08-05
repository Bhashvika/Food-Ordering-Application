package com.tap.Dao;

import java.util.List;

import com.tap.models.UserModel;

public interface UserDao {
     List<UserModel> getAllUsers();
     UserModel getUser(int userid);
     void insertUser(UserModel u);
     void updateUser(int userid,UserModel u);
     void deleteUser(int userid);
}
