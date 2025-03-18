package com.pan.pRPCBase;

import com.pan.pRPCBase.config.RPCConfig;
import com.pan.pRPCBase.config.RegistryConfig;
import com.pan.pRPCBase.constant.RPCConstant;
import com.pan.pRPCBase.registry.Registry;
import com.pan.pRPCBase.registry.RegistryFactory;
import com.pan.pRPCBase.utils.ConfigUtils;
import lombok.extern.slf4j.Slf4j;

/**
 * RPC 框架应用
 * 相当于 holder，存放了项目全局用到的变量。双检锁单例模式实现
 */
@Slf4j
public class RPCApplication {

    private static volatile RPCConfig rpcConfig;

    /**
     * 初始化框架，支持传入自定义配置
     */
    public static void init(RPCConfig newRpcConfig) {
        rpcConfig = newRpcConfig;
        log.info("rpc初始化完成, config = {}", newRpcConfig.toString());
        // 注册中心初始化
        RegistryConfig registryConfig = rpcConfig.getRegistryConfig();
        Registry registry = RegistryFactory.getInstance(registryConfig.getRegistry());
        registry.init(registryConfig);
        log.info("注册初始化完成, config = {}", registryConfig);
    }

    public static void init(){
        RPCConfig rpcConfig;
        try{
            rpcConfig = ConfigUtils.loadConfig(RPCConfig.class, RPCConstant.DEFAULT_CONFIG_PREFIX);
        }catch (Exception e){
            rpcConfig = new RPCConfig();
        }
        init(rpcConfig);
    }

    // 获取配置
    public static RPCConfig getRpcConfig() {
        if (rpcConfig == null) {
            synchronized (RPCApplication.class) {
                if (rpcConfig == null) {
                    init();
                }
            }
        }
        return rpcConfig;
    }
}
