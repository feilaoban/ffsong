package com.mugu.blog.friend.links.common.model.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class FriendLinksAddReq {

    @ApiModelProperty(value = "博客名称",required = true)
    private String blogName;

    @ApiModelProperty(value = "博客地址",required = true)
    private String blogUrl;
}
