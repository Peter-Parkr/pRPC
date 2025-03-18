package com.pan.pRPCConsumer;

import com.pan.pRPCBase.config.RPCConfig;
import com.pan.pRPCBase.utils.ConfigUtils;

public class ConsumerPropertiesTest {
    public static void main(String[] args) {
        RPCConfig rpc = ConfigUtils.loadConfig(RPCConfig.class, "rpc");
        System.out.println(rpc);
    }
}
