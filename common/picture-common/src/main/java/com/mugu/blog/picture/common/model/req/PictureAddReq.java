package com.mugu.blog.picture.common.model.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class PictureAddReq {
    @ApiModelProperty(value = "名字",required = true)
    private String name;

    @ApiModelProperty(value = "地点",required = true)
    private String location;

    @ApiModelProperty(value = "描述",required = true)
    private String description;
}
