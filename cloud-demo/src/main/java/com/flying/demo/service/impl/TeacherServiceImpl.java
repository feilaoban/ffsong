package com.flying.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.flying.demo.common.annotation.DataSource;
import com.flying.demo.dao.TeacherMapper;
import com.flying.demo.pojo.entity.Teacher;
import com.flying.demo.service.TeacherService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author songfeifei
 * @Date 2022/5/30 18:12
 * @Description
 */
@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements TeacherService {

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
}
