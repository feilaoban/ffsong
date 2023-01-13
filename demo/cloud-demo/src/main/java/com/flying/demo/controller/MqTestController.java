package com.flying.demo.controller;

import com.flying.demo.service.MqTestService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author songfeifei
 * @Date 2022/5/11 17:27
 * @Description
 */
@Slf4j
@RestController
@RequestMapping("/mq")
public class MqTestController {

    @Autowired
    MqTestService mqTestService;

    @ApiOperation(value = "mq发送消息-消费消息测试")
    @GetMapping("/test")
    public void testMq(@RequestParam("msg") String msg) {
        mqTestService.testMq(msg);
    }
}
