package com.flying.demo.controller;

import com.flying.demo.service.ThreadLocalTestService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @Author songfeifei
 * @Date 2022/5/20 10:48
 * @Description
 */
@Slf4j
@RestController
@RequestMapping("/threadLocal")
public class ThreadLocalTestController {

    @Autowired
    ThreadLocalTestService threadLocalTestService;

    @ApiOperation(value = "批量导入")
    @PostMapping("/import")
    public void testMq(@RequestParam("file") MultipartFile file) throws IOException {
        threadLocalTestService.batchSave(file);
    }
}
