package com.flying.demo.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.flying.demo.dao.TeacherMapper;
import com.flying.demo.pojo.entity.Teacher;
import com.flying.demo.service.TeacherService;
import org.springframework.stereotype.Service;

/**
 * @Author songfeifei
 * @Date 2022/5/30 18:12
 * @Description
 */
@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements TeacherService {

    @Override
    @DS("slave")
    public Teacher getOneById(Long teacherId) {
        return this.getById(teacherId);
    }
}
