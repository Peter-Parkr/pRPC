package com.pan.pPRCBase;

import com.pan.pPRCBase.config.RPCConfig;
import com.pan.pPRCBase.constant.RPCConstant;
import com.pan.pPRCBase.utils.ConfigUtils;
import lombok.extern.slf4j.Slf4j;

/**
 * RPC 框架应用
 * 相当于 holder，存放了项目全局用到的变量。双检锁单例模式实现
 */
@Slf4j
public class RPCApplication {
    private static volatile RPCConfig rpcConfig;
    // 初始化框架
    public static void init(RPCConfig rpcConfig) {
        RPCApplication.rpcConfig = rpcConfig;
        log.info("rpc 初始化ing, config = {}", rpcConfig.toString());
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
