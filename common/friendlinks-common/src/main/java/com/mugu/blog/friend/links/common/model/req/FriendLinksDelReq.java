package com.mugu.blog.friend.links.common.model.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class FriendLinksDelReq {
    @ApiModelProperty(value = "主键ID")
    @NotNull
    private Long id;
}
