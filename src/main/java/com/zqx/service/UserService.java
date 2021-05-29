package com.zqx.service;

import com.zqx.pojo.User;

public interface UserService {

    public void registerUser(User user);

    public User login(User user);

    /**
     *功能描述 判断用户名是否可用
     * @author zqx
     * @date 2021/5/28
      * @param username
     * @return boolean false为用户名可用
     */
    public boolean existsUsername(String username);

}
