package com.flying.demo.controller;

import com.flying.demo.service.TeacherService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author songfeifei
 * @Date 2022/5/11 17:27
 * @Description
 */
@Slf4j
@RestController
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    TeacherService teacherService;

    @ApiOperation(value = "查询")
    @GetMapping("/test/{teacherId}")
    public void testMq(@PathVariable("teacherId") Long teacherId) {
        teacherService.getOneById(teacherId);
    }
}
