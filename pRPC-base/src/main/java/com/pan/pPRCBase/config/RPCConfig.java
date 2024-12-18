package com.pan.pPRCBase.config;

import lombok.Data;

/**
 * RPC 框架配置
 */
@Data
public class RPCConfig {
    /**
     * 名称
     */
    private String name = "pRPC";

    /**
     * 版本号
     */
    private String version = "1.0";

    /**
     * 服务器主机名
     */
    private String serverHost = "localhost";

    /**
     * 服务器端口号
     */
    private Integer serverPort = 8080;

    /**
     * 模拟调用 mock
     */
    private boolean mock = false;
}
