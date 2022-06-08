package com.flying.demo.controller;

import com.flying.demo.pojo.entity.Teacher;
import com.flying.demo.service.TeacherService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @GetMapping("/query")
    public void query(@RequestParam("teacherId") Long teacherId) {

        // DB：ffsong Teacher.name = 李老师
        Teacher teacher = teacherService.getOneById(teacherId);
        System.out.println("DB：ffsong Teacher.name = " + teacher.getCnName());

        // DB：ffsong_1 Teacher.name = 李老师_1
        List<Teacher> teachers = teacherService.getAll();
        System.out.println("DB：ffsong_1 Teacher.name = " + teachers.get(0).getCnName());
    }
}
