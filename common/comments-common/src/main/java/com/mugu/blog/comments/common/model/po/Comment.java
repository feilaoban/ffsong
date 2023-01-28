package com.mugu.blog.comments.common.model.po;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    private Long id;

    @ApiModelProperty(value = "评论ID")
    private String commentId;

    @ApiModelProperty(value = "内容")
    private String content;

    @ApiModelProperty(value = "父评论id，如果为顶级则为null")
    private String pid;

    @ApiModelProperty(value = "姓名")
    private String nickName;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "文章id")
    private Long articleId;

    @ApiModelProperty(value = "回复人姓名")
    private String replyName;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    @ApiModelProperty(value = "状态 1 正常 0 已删除")
    private Integer status;

}