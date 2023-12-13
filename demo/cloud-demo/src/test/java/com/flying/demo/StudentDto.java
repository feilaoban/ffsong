package com.flying.demo;

import com.flying.demo.pojo.entity.Student;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Author songfeifei
 * @Date 2023/8/7 10:55
 * @Description
 */
@Data
public class StudentDto implements Serializable {

    private List<Student> students;

}
