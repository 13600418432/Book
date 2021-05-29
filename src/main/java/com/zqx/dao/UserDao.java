package com.zqx.dao;

import com.zqx.pojo.User;

import java.util.List;

public interface UserDao {

    public User queryUserByUsername(String username);

    public User queryUserByUsernameAndPassword(String username, String password);

    public int saveUser(User user);

}
