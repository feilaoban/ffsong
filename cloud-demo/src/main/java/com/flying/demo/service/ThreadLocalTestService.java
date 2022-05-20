package com.flying.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.flying.demo.pojo.entity.Student;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @Author songfeifei
 * @Date 2022/5/19 16:14
 * @Description
 */
public interface ThreadLocalTestService extends IService<Student> {

    /**
     * excel批量导入
     * @param file
     * @throws IOException
     */
    void batchSave(MultipartFile file) throws IOException;
}
