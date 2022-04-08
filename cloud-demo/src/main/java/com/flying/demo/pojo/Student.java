package com.flying.demo.pojo;

import com.flying.demo.common.annotation.CaseConversion;
import lombok.Data;

/**
 * @Author songfeifei
 * @Date 2022/3/28 16:17
 * @Description
 */
@Data
public class Student {

    private String cnName;

    @CaseConversion(convert = true)
    public String enName;

    private String gender;
}
