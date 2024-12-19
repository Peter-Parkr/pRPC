package com.pan.pPRCBase.proxy;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.pan.pPRCBase.RPCApplication;
import com.pan.pPRCBase.model.RPCRequest;
import com.pan.pPRCBase.model.RpcResponse;
import com.pan.pPRCBase.serializer.JDKSerializer;
import com.pan.pPRCBase.serializer.Serializer;
import com.pan.pPRCBase.serializer.SerializerFactory;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * JDK 动态代理
 */
public class ServiceProxy implements InvocationHandler {
    /**
     * 调用代理
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 指定序列化器
        // Serializer serializer = new JDKSerializer();
        final Serializer serializer = SerializerFactory.getInstance(RPCApplication.getRpcConfig().getSerializer());
        // 构造请求
        RPCRequest rpcRequest = RPCRequest.builder()
                .serviceName(method.getDeclaringClass().getName())
                .methodName(method.getName())
                .parameterTypes(method.getParameterTypes())
                .args(args)
                .build();
        try {
            // 序列化
            byte[] bodyBytes = serializer.serialize(rpcRequest);
            // 发送请求
            // todo 注意，这里地址被硬编码了（需要使用注册中心和服务发现机制解决）
            try (HttpResponse httpResponse = HttpRequest.post("http://localhost:8081")
                    .body(bodyBytes)
                    .execute()) {
                byte[] result = httpResponse.bodyBytes();
                // 反序列化
                RpcResponse rpcResponse = (RpcResponse) serializer.deserialize(result, RpcResponse.class);
                return rpcResponse.getData();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
