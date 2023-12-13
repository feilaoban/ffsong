package com.flying.demo.pojo.dto;

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
public class StudentDeepCloneDTO implements Serializable {

    private List<Student> students;

}
