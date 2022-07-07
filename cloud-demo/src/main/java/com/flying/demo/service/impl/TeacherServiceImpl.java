package com.flying.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.flying.demo.common.annotation.DataSource;
import com.flying.demo.dao.TeacherMapper;
import com.flying.demo.pojo.entity.Student;
import com.flying.demo.pojo.entity.Teacher;
import com.flying.demo.service.StudentService;
import com.flying.demo.service.TeacherService;
import com.flying.demo.utils.SpringBeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author songfeifei
 * @Date 2022/5/30 18:12
 * @Description
 */
@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements TeacherService {

    @Autowired
    StudentService studentService;

    @Override
    @DataSource(name = "master")
    public Teacher getOneById(Long teacherId) {
        return this.getById(teacherId);
    }

    @Override
    @DataSource(name = "slave")
    public List<Teacher> getAll() {
        return this.list();
    }

/*
    // 在类内部调用调用类内部@Transactional标注的方法，会导致事务失效
    @Override
    public void saveTeacher() {
        save();
    }

    @Transactional(rollbackFor = Exception.class)
    public void save() {
        Teacher teacher = new Teacher();
        teacher.setCnName("王老师");
        save(teacher);

        Student student = new Student();
        student.setCnName("王老师-小明");
        studentService.save(student);

        throw new RuntimeException("事务测试");
    }*/

    // -------------------解决方法如下-------------------

    @Override
    public void saveTeacher() {
        TeacherServiceImpl teacherService = SpringBeanUtil.getBean(TeacherServiceImpl.class);
        teacherService.save();
    }

    @Transactional(rollbackFor = Exception.class)
    public void save() {
        Teacher teacher = new Teacher();
        teacher.setCnName("王老师");
        save(teacher);

        Student student = new Student();
        student.setCnName("王老师-小明");
        studentService.save(student);

        throw new RuntimeException("事务测试");
    }
}
