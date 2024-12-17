package com.pan.pRPCProvider;

import com.pan.pRPCCommon.model.User;

public class UserServiceImpl {

    public User getUser(User user) {
        System.out.println("用户名为：" + user.getName());
        return user;
    }
}
