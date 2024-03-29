package com.flying.demo.controller;

import com.flying.demo.pojo.entity.Student;
import com.flying.demo.service.StudentService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @Author songfeifei
 * @Date 2022/5/20 10:48
 * @Description
 */
@Slf4j
@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentService studentService;

    @ApiOperation(value = "批量导入", tags = "ThreadLocal")
    @PostMapping("/import")
    public void batchSave(@RequestParam("file") MultipartFile file) throws IOException {
        studentService.batchSave(file);
    }

    @ApiOperation(value = "导出")
    @PostMapping("/export")
    public void export(HttpServletResponse response) {
        studentService.export(response);
    }

    @ApiOperation(value = "查询", tags = "多数据源")
    @GetMapping("/query")
    public void query(@RequestParam("studentId") Long studentId, @RequestParam("teacherId") Long teacherId) {

        // DB：ffsong Student.name = 小明
        Student student = studentService.getOneById(studentId);
        System.out.println("DB：ffsong Student.name = " + student.getCnName());

        // DB：ffsong_1 Student.name = 小明_1
        List<Student> students = studentService.getAll();
        System.out.println("DB：ffsong_1 Student.name = " + students.get(0).getCnName());
    }
}
