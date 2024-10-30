package com.mayab.quality.loginunittest.dao;

import com.mayab.quality.loginunittest.model.User;

import java.sql.SQLException;
import java.util.List;


public interface IDAOUser {
    User findByUserName(String name);

    int save(User user) throws SQLException;

    User findUserByEmail(String email);

    List<User> findAll();

    User findById(int id);

    boolean deleteById(int id);

    User updateUser(User userOld);


}