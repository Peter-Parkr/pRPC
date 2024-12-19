package com.pan.pPRCBase.server;


import com.pan.pPRCBase.RPCApplication;
import com.pan.pPRCBase.model.RPCRequest;
import com.pan.pPRCBase.model.RpcResponse;
import com.pan.pPRCBase.registry.LocalRegistry;
import com.pan.pPRCBase.serializer.JDKSerializer;
import com.pan.pPRCBase.serializer.Serializer;
import com.pan.pPRCBase.serializer.SerializerFactory;
import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;

import javax.swing.*;
import java.io.IOException;
import java.lang.reflect.Method;

public class HttpServerHandler implements Handler<HttpServerRequest> {
    /**
     * Http 请求处理
     */
    @Override
    public void handle(HttpServerRequest httpServerRequest) {
        // 指定序列化器
        final Serializer serializer = SerializerFactory.getInstance(RPCApplication.getRpcConfig().getSerializer());
        // 记录日志
        System.out.println("接受请求: " + httpServerRequest.method() + " " + httpServerRequest.uri());
        // 异步处理 Http 请求
        httpServerRequest.bodyHandler(body -> {
            // 反序列化
            byte[] bytes = body.getBytes();
            RPCRequest rpcRequest = null;
            try {
                rpcRequest = (RPCRequest) serializer.deserialize(bytes, RPCRequest.class);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            // 构造响应结果对象
            RpcResponse rpcResponse = new RpcResponse();
            // 如果请求为 null，直接返回
            if (rpcRequest == null) {
                rpcResponse.setMessage("rpcRequest 为空");
                doResponse(httpServerRequest, rpcResponse, serializer);
                return;
            }
            // 不为空
            try {
                // 获取要调用的服务实现类，通过反射调用
                Class<?> implClass = LocalRegistry.get(rpcRequest.getServiceName());
                Method method = implClass.getMethod(rpcRequest.getMethodName(), rpcRequest.getParameterTypes());
                Object result = method.invoke(implClass.newInstance(), rpcRequest.getArgs());
                // 封装返回结果
                rpcResponse.setData(result);
                rpcResponse.setDataType(method.getReturnType());
                rpcResponse.setMessage("ok");
            } catch (Exception e) {
                e.printStackTrace();
                rpcResponse.setMessage(e.getMessage());
                rpcResponse.setException(e);
            }
            // 响应
            doResponse(httpServerRequest, rpcResponse, serializer);


        });
    }

    /**
     * 响应
     */
    void doResponse(HttpServerRequest request, RpcResponse rpcResponse, Serializer serializer) {
        HttpServerResponse httpServerResponse = request.response()
                .putHeader("content-type", "application/json");
        try {
            // 序列化
            byte[] serialized = serializer.serialize(rpcResponse);
            httpServerResponse.end(Buffer.buffer(serialized));
        } catch (IOException e) {
            e.printStackTrace();
            httpServerResponse.end(Buffer.buffer());
        }
    }


}
