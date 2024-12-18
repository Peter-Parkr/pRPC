package com.pan.pPRCBase.server;

import io.vertx.core.Vertx;

public class VertxHttpServer implements HttpServer {

    @Override
    public void doStart(int port) {
        // 1. 创建 Vert.x 实例
        Vertx vertx = Vertx.vertx();
        // 2. 创建 Http 服务器
        io.vertx.core.http.HttpServer server = vertx.createHttpServer();
        // 3. 监听端口 并 处理请求
        /*
        server.requestHandler(request -> {
            // 处理 Http 请求
            System.out.println("接受请求：" + request.method() + " " + request.uri());
            // 发送 Http 响应
            request.response().putHeader("content-type", "text/plain")
                    .end("Hello from Vert.x HTTP server!");
        });
        */
        // 使用 `server.requestHandler` 绑定请求处理器。
        server.requestHandler(new HttpServerHandler());

        // 4. 启动 HTTP 服务器 并 监听 指定端口
        server.listen(port, result -> {
            if (result.succeeded()){
                System.out.println("正在监听：" + port);
            } else {
                System.err.println("开启服务器失败：" + result.cause());
            }
        });

    }
}
