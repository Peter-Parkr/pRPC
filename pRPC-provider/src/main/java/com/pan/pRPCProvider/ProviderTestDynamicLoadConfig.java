package com.pan.pRPCProvider;

import com.pan.pRPCBase.RPCApplication;
import com.pan.pRPCBase.registry.LocalRegistry;
import com.pan.pRPCBase.server.VertxHttpServer;
import com.pan.pRPCBase.server.HttpServer;
import com.pan.pRPCCommon.service.UserService;

public class ProviderTestDynamicLoadConfig {

    public static void main(String[] args) throws Exception {
        // RPC 框架初始化
        RPCApplication.init();

        // 注册服务
        LocalRegistry.register(UserService.class.getName(), UserServiceImpl.class);

        // 启动 web 服务
        HttpServer httpServer = new VertxHttpServer();
        httpServer.doStart(RPCApplication.getRpcConfig().getServerPort());
    }
}
