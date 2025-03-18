package com.pan.pRPCProvider;

import com.pan.pRPCBase.RPCApplication;
import com.pan.pRPCBase.config.RPCConfig;
import com.pan.pRPCBase.config.RegistryConfig;
import com.pan.pRPCBase.model.ServiceMetaInfo;
import com.pan.pRPCBase.registry.LocalRegistry;
import com.pan.pRPCBase.registry.Registry;
import com.pan.pRPCBase.registry.RegistryFactory;
import com.pan.pRPCBase.server.HttpServer;
import com.pan.pRPCBase.server.VertxHttpServer;
import com.pan.pRPCCommon.service.UserService;

public class Provider1 {
    // 提供服务
    public static void main(String[] args) throws Exception {
        // 初始化
        RPCApplication.init();

        // 注册服务
        String serviceName = UserService.class.getName();
        LocalRegistry.register(serviceName, UserServiceImpl.class);
        // 注册服务到注册中心
        RPCConfig rpcConfig = RPCApplication.getRpcConfig();
        RegistryConfig registryConfig = rpcConfig.getRegistryConfig();
        Registry registry = RegistryFactory.getInstance(registryConfig.getRegistry());
        ServiceMetaInfo serviceMetaInfo = new ServiceMetaInfo();
        serviceMetaInfo.setServiceName(serviceName);
        serviceMetaInfo.setServiceHost(rpcConfig.getServerHost());
        serviceMetaInfo.setServicePort(rpcConfig.getServerPort());
        registry.register(serviceMetaInfo);

        // 启动 web 服务
        HttpServer httpServer = new VertxHttpServer();
        httpServer.doStart(RPCApplication.getRpcConfig().getServerPort());
    }
}
