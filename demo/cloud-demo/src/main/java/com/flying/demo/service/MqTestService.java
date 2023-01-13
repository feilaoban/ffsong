package com.flying.demo.service;

/**
 * @Author songfeifei
 * @Date 2022/5/11 17:23
 * @Description
 */
public interface MqTestService {

    /**
     * mqc测试
     * @param msg 消息内容
     */
    void testMq(String msg);
}
