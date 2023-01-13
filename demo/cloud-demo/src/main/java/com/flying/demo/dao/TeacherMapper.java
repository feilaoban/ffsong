package com.flying.demo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.flying.demo.pojo.entity.Teacher;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author songfeifei
 * @Date 2022/5/20 11:04
 * @Description
 */
@Mapper
public interface TeacherMapper extends BaseMapper<Teacher> {
}
