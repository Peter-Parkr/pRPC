package com.pan.pPRCBase.registry;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 本地注册服务器
 * 作用：根据服务名 获取到 对应的实现类
 */
public class LocalRegistry {
    // 使用线程安全的 ConcurrentHashMap 存储服务注册信息
    // key : 服务名称
    // value : 服务的实现类

    public static final Map<String, Class<?>> map = new ConcurrentHashMap<>();

    // 注册服务
    public static void register(String ServiceName, Class<?> implClass){
        map.put(ServiceName, implClass);
    }
    // 获取服务
    public static Class<?> get(String ServiceName){
        return map.get(ServiceName);
    }
    // 删除服务
    public static void remove(String ServiceName){
        map.remove(ServiceName);
    }

}
