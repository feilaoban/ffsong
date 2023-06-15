package com.flying.demo.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.flying.demo.dao.StudentMapper;
import com.flying.demo.handler.ErrorContextHandler;
import com.flying.demo.pojo.dto.StudentDTO;
import com.flying.demo.pojo.entity.Student;
import com.flying.demo.service.StudentService;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author songfeifei
 * @Date 2022/5/19 16:15
 * @Description
 */
@Service
@DS("master")
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {

    @Override
    public void batchSave(MultipartFile file) throws IOException {
        // EasyExcel
        List<StudentDTO> studentDTOS = EasyExcel.read(file.getInputStream())
                .headRowNumber(1)
                .ignoreEmptyRow(true)
                .head(StudentDTO.class)
                .sheet()
                .doReadSync();
        if (CollUtil.isEmpty(studentDTOS)) {
            throw new RuntimeException("导入的文件内容为空!");
        }
        List<Student> students = studentDTOS.stream().map(studentDTO -> {
            Student student = new Student();
            BeanUtil.copyProperties(studentDTO, student);
            return student;
        }).collect(Collectors.toList());

        if (!saveStudent(students)) {
            // 打印错误信息
            String printMsg = ErrorContextHandler.printMsg();
            ErrorContextHandler.clear();
            System.out.println(printMsg);
        }
    }

    @SneakyThrows
    @Override
    public void export(HttpServletResponse response) {
        List<Student> students = getAll();
        String title = "学生列表" + LocalDate.now();

        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        response.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode(title, "UTF-8") + ".xlsx");

        ExcelWriter excelWriter = EasyExcel.write(response.getOutputStream()).build();
        WriteSheet sheet1 = EasyExcel.writerSheet(0, "学生列表").head(Student.class).build();
        excelWriter.write(students, sheet1);
        excelWriter.finish();
    }

    @Override
    public Student getOneById(Long studentId) {
        return this.getById(studentId);
    }

    @Override
    @DS("slave")
    public List<Student> getAll() {
        return this.list();
    }

    /**
     * 保存学生信息
     * @param students
     * @return
     */
    private boolean saveStudent(List<Student> students) {
        boolean flag = true;
        for (Student student : students) {
            if (StrUtil.isBlank(student.getCnName())) {
                ErrorContextHandler.putError(ErrorContextHandler.ErrorEnum.E_CN_NAME.getType(), student.getCnName());
                flag = false;
                continue;
            }
            if (StrUtil.isBlank(student.getGender())) {
                ErrorContextHandler.putError(ErrorContextHandler.ErrorEnum.E_GENDER.getType(), student.getCnName());
                flag = false;
            }
        }
        // 保存
        this.saveBatch(students);
        return flag;
    }
}
