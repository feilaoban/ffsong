package com.flying.demo.pojo.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.flying.demo.common.annotation.CaseConversion;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @Author songfeifei
 * @Date 2022/3/28 16:17
 * @Description
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "Teacher", description = "老师信息表")
public class Teacher extends Model<Teacher> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "guid", type = IdType.ASSIGN_ID)
    private Long guid;

    @ExcelProperty(value = "中文名", index = 1)
    @ApiModelProperty(value = "中文名")
    private String cnName;

    @ExcelProperty(value = "英文名")
    @ApiModelProperty(value = "英文名")
    @CaseConversion(convert = true)
    public String enName;

    @ExcelProperty(value = "性别")
    @ApiModelProperty(value = "性别")
    private String gender;
}
