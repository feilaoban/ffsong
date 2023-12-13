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
    // 在类内部调用调用类内部@Transactional标注的方法，会导致事务失效。
    // @Transactional加在外部方法saveTeacher()正常。即，必须在入口方法中使用@Transactional。
    // 原理说明：类内部调用是this.method()方式调用，没有使用代理对象，只有代理对象对@Transactional标注的方法进行了增强，因为原始多想调用时事务会失效
    // 解决：获取代理类对象，并调用方法，详见 68 行

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
