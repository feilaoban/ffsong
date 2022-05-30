package com.flying.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.flying.demo.pojo.entity.Teacher;

/**
 * @Author songfeifei
 * @Date 2022/5/30 18:12
 * @Description
 */
public interface TeacherService extends IService<Teacher> {

    /**
     * 根据ID获取学生信息
     * @param teacherId
     * @return
     */
    Teacher getOneById(Long teacherId);

}
