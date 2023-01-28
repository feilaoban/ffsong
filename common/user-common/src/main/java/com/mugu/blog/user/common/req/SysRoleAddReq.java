package com.mugu.blog.user.common.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class SysRoleAddReq {
    @ApiModelProperty(value = "角色名称",required = true)
    @NotBlank
    private String name;

    @ApiModelProperty(value = "角色编码",required = true)
    @NotBlank
    private String code;
}
