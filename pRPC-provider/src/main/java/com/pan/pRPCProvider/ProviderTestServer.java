package com.pan.pRPCProvider;

import com.pan.pPRCBase.registry.LocalRegistry;
import com.pan.pPRCBase.server.VertxHttpServer;
import com.pan.pRPCCommon.service.UserService;

public class ProviderTestServer {
    public static void main(String[] args) {
        // 注册服务
        LocalRegistry.register(UserService.class.getName(), UserServiceImpl.class);
        // 启动 web 服务
        VertxHttpServer vertxHttpServer = new VertxHttpServer();
        vertxHttpServer.doStart(11111);
    }
}
