package com.pan.pRPCBase.loadbalancer;

/**
 * 列举所有支持的负载均衡器键名。
 */
public interface LoadBalancerKeys {

    /**
     * 轮询
     */
    String ROUND_ROBIN = "roundRobin";

    String RANDOM = "random";

    String CONSISTENT_HASH = "consistentHash";

}
