package com.flying.demo.pojo.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.flying.demo.common.annotation.CaseConversion;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author songfeifei
 * @Date 2022/3/28 16:17
 * @Description
 */
@Data
public class StudentDTO extends Model<StudentDTO> {

    private static final long serialVersionUID = 1L;

    @ExcelProperty(value = "中文名")
    @ApiModelProperty(value = "中文名", required = true)
    private String cnName;

    @ExcelProperty(value = "英文名")
    @ApiModelProperty(value = "英文名", required = true)
    @CaseConversion(convert = true)
    public String enName;

    @ExcelProperty(value = "性别")
    @ApiModelProperty(value = "性别", required = true)
    private String gender;
}
