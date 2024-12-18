package com.pan.pRPCConsumer;

import com.pan.pPRCBase.config.RPCConfig;
import com.pan.pPRCBase.utils.ConfigUtils;

public class ConsumerPropertiesTest {
    public static void main(String[] args) {
        RPCConfig rpc = ConfigUtils.loadConfig(RPCConfig.class, "rpc");
        System.out.println(rpc);
    }
}
