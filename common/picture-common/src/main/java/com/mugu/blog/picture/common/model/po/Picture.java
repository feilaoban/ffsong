package com.mugu.blog.picture.common.model.po;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Picture implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键ID")
    private Long id;

    @ApiModelProperty(value = "名字")
    private String name;

    @ApiModelProperty(value = "地点")
    private String location;

    @ApiModelProperty(value = "图片url")
    private String url;

    @ApiModelProperty(value = "图片的md5")
    private String md5;

    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty(value = "创建的用户")
    private String userId;

    @ApiModelProperty(value = "状态 1 正常 0 删除")
    private Integer status;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

}