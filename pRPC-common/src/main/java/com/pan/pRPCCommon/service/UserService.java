package com.pan.pRPCCommon.service;

import com.pan.pRPCCommon.model.User;

public interface UserService {
    User getUser(User user);

    /**
     * 新方法 - 获取数字
     * 测试 mock 功能
     */
    default short getNumber() {
        return 1;
    }
}
