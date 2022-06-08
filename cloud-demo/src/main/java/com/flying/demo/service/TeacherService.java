package com.flying.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.flying.demo.pojo.entity.Teacher;

import java.util.List;

/**
 * @Author songfeifei
 * @Date 2022/5/30 18:12
 * @Description
 */
public interface TeacherService extends IService<Teacher> {

    /**
     * 根据ID获取老师信息
     * @param teacherId
     * @return
     */
    Teacher getOneById(Long teacherId);

    /**
     * 获取全部老师信息
     * @return
     */
    List<Teacher> getAll();

}
