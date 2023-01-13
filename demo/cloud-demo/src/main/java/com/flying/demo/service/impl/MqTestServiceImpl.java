package com.flying.demo.service.impl;

import com.flying.demo.handler.MqPushHandler;
import com.flying.demo.service.MqTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author songfeifei
 * @Date 2022/5/11 17:24
 * @Description
 */
@Service
public class MqTestServiceImpl implements MqTestService {

    @Autowired
    MqPushHandler mqPushHandler;

    @Override
    public void testMq(String msg) {
        mqPushHandler.sendMsg(msg);
    }
}
