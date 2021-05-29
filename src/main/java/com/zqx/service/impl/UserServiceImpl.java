package com.zqx.service.impl;

import com.zqx.dao.UserDao;
import com.zqx.dao.impl.UserDaoImpl;
import com.zqx.pojo.User;
import com.zqx.service.UserService;

public class UserServiceImpl implements UserService {

    UserDao userDao = new UserDaoImpl();

    @Override
    public void registerUser(User user) {
        userDao.saveUser(user);
    }

    @Override
    public User login(User user) {
        return userDao.queryUserByUsernameAndPassword(user.getUsername(),user.getPassword());
    }

    @Override
    public boolean existsUsername(String username) {
        User user = userDao.queryUserByUsername(username);
        if (user == null){
            return false;
        }else {
            return true;
        }
    }
}
