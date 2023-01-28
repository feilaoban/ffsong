package com.mugu.blog.friend.links.common.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class FriendLinksVo  {

    @ApiModelProperty(value = "主键ID")
    private Long id;

    @ApiModelProperty(value = "博客名称")
    private String blogName;

    @ApiModelProperty(value = "博客地址")
    private String blogUrl;

    @ApiModelProperty(value = "图片地址")
    private String imageUrl;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+8")
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
}