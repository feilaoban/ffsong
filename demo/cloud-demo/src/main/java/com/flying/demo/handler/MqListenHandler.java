package com.flying.demo.handler;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author songfeifei
 * @Date 2022/4/7 17:56
 * @Description 消费消息
 */
@Component
@Slf4j
public class MqListenHandler implements MessageListenerConcurrently {
    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
        for (MessageExt msgExt : list) {
            // 这里可以加入幂等
            String tag = msgExt.getTags();
            if (StrUtil.equals(tag, "TEST_TAG")) {
                consumeTestMsg(msgExt);
            }
            if (StrUtil.equals(tag, "TEST_TAG_1")) {

            }
            // ...
        }
        return null;
    }

    private void consumeTestMsg(MessageExt msgExt) {
        String msg = new String(msgExt.getBody());
        System.out.println("消费到MQ消息，内容为：" + msg);
    }
}
