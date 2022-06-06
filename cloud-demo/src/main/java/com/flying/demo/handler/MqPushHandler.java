package com.flying.demo.handler;

import com.flying.demo.config.MqParamConfig;
import com.flying.demo.config.MqProducerConfig;
import lombok.SneakyThrows;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author songfeifei
 * @Date 2022/5/11 17:08
 * @Description 生产消息
 */
@Component
public class MqPushHandler {

    @Autowired
    MqProducerConfig mqProducerConfig;
    @Autowired
    MqParamConfig mqParamConfig;

    /**
     * 发送测试消息
     * @param msg 消息内容
     */
    @SneakyThrows
    public void sendMsg(String msg) {
        Message message = new Message(mqParamConfig.getTopic(), "TEST_TAG", msg.getBytes(RemotingHelper.DEFAULT_CHARSET));
        mqProducerConfig.getProducer().send(message);
    }
}
