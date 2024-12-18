package com.pan.pRPCProvider;

import com.pan.pPRCBase.RPCApplication;
import com.pan.pPRCBase.registry.LocalRegistry;
import com.pan.pPRCBase.server.VertxHttpServer;
import com.pan.pPRCBase.server.HttpServer;
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
