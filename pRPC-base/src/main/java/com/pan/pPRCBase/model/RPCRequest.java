package com.pan.pPRCBase.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.pan.pPRCBase.constant.RPCConstant;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RPCRequest implements Serializable {
    // 服务名称
    public String serviceName;
    // 方法名称
    public String methodName;
    // 参数类型列表
    public Class<?>[] parameterTypes;
    // 参数列表
    public Object[] args;
    /**
     * 服务版本
     */
    private String serviceVersion = RPCConstant.DEFAULT_SERVICE_VERSION;
}
