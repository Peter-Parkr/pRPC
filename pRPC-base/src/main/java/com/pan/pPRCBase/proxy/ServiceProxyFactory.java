package com.pan.pPRCBase.proxy;

import com.pan.pPRCBase.RPCApplication;

import java.lang.reflect.Proxy;

public class ServiceProxyFactory {
    /**
     * 根据服务类获取代理对象
     */
    public static <T> T getProxy(Class<T> serviceClass) {
        if (RPCApplication.getRpcConfig().isMock()) {
            return getMockProxy(serviceClass);
        }
        return (T) Proxy.newProxyInstance(
                serviceClass.getClassLoader(),
                new Class[]{serviceClass},
                new ServiceProxy());
    }

    /**
     * 新增获取 mock 代理对象的方法 `getMockProxy`。
     * 可以通过读取已定义的全局配置 `mock` 来区分创建哪种代理对象。
     */
    public static <T> T getMockProxy(Class<T> serviceClass) {
        return (T) Proxy.newProxyInstance(
                serviceClass.getClassLoader(),
                new Class[]{serviceClass},
                new MockServiceProxy());
    }
}
