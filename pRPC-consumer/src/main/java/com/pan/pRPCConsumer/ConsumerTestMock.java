package com.pan.pRPCConsumer;

import com.pan.pPRCBase.proxy.ServiceProxyFactory;
import com.pan.pRPCCommon.model.User;
import com.pan.pRPCCommon.service.UserService;

public class ConsumerTestMock {
    public static void main(String[] args) {
        User user = new User();
        UserService userService = ServiceProxyFactory.getProxy(UserService.class);
        user.setName("ppp");

        User newUser = userService.getUser(user);
        if (newUser != null) {
            System.out.println(newUser.getName());
        } else {
            System.out.println("user == null");
        }

        long number = userService.getNumber();
        System.out.println(number);

    }
}
