package com.pan.pRPCCommon.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

//对象实现序列化接口，为后续网络传输序列化提供支持
@Setter
@Getter
public class User implements Serializable {
    public String name;
}
