package com.pan.pPRCBase.server;

public interface HttpServer {
    /**
     * 启动服务器
     */
    void doStart(int port) throws Exception;
}
