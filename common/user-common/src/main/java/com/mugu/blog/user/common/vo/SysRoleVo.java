package com.mugu.blog.user.common.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class SysRoleVo {
    @ApiModelProperty("主键ID")
    private Long id;

    @ApiModelProperty(value = "角色名称")
    private String name;

    @ApiModelProperty(value = "角色编码")
    private String code;
}
