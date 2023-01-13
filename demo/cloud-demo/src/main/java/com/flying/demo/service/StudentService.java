package com.flying.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.flying.demo.pojo.entity.Student;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @Author songfeifei
 * @Date 2022/5/19 16:14
 * @Description
 */
public interface StudentService extends IService<Student> {

    /**
     * excel批量导入
     * @param file
     * @throws IOException
     */
    void batchSave(MultipartFile file) throws IOException;

    /**
     * 根据ID获取学生信息
     * @param studentId
     * @return
     */
    Student getOneById(Long studentId);

    /**
     * 获取全部学生信息
     * @return
     */
    List<Student> getAll();

}
