package com.mugu.blog.user.common.vo;

import com.mugu.blog.user.common.po.SysRole;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class SysRolePermissionVo {

    @ApiModelProperty(value = "主键ID")
    private Long id;

    @ApiModelProperty(value = "接口url")
    private String url;

    @ApiModelProperty(value = "接口描述")
    private String name;

    @ApiModelProperty(value = "角色列表")
    private List<SysRole> roles;

}
