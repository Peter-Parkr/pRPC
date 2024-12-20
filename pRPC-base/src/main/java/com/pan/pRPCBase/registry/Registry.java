package com.pan.pRPCBase.registry;

import com.pan.pRPCBase.config.RegistryConfig;
import com.pan.pRPCBase.model.ServiceMetaInfo;

import java.util.List;

public interface Registry {
    /**
     * 初始化
     */
    void init(RegistryConfig config);

    /**
     * 注册服务（服务端）
     */
    void register(ServiceMetaInfo info) throws Exception;

    /**
     * 注销服务（服务端）
     */
    void unRegister(ServiceMetaInfo serviceMetaInfo);

    /**
     * 服务发现（获取某服务的所有节点，消费端）
     * @param serviceKey 服务键名
     */
    List<ServiceMetaInfo> serviceDiscovery(String serviceKey);

    /**
     * 服务销毁
     */
    void destroy();
}
