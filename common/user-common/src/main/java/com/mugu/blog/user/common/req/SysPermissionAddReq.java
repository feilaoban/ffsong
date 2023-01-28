package com.mugu.blog.user.common.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class SysPermissionAddReq {

    @ApiModelProperty(value = "权限名称")
    private String name;

    @ApiModelProperty(value = "权限的url")
    private String url;
}
