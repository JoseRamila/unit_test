package com.mayab.quality.loginunittest.dao;

import com.mayab.quality.loginunittest.model.User;


public interface IDAOUser {
    User findbyUserName(String name);
}
