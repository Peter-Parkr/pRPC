package com.pan.pRPCConsumer;

import com.pan.pRPCCommon.model.User;
import com.pan.pRPCCommon.service.UserService;

public class Consumer {
    public static void main(String[] args) {
        UserService userService = null;
        User user = new User();
        user.setName("潘");

        User newUser = userService.getUser(user);
        if(newUser != null) {
            System.out.println(user.getName());
        }else{
            System.out.println("user is null");
        }
    }
}
