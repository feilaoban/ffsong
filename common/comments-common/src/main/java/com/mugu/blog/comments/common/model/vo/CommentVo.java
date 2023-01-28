package com.mugu.blog.comments.common.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class CommentVo {

    @ApiModelProperty(value = "主键id")
    private Long id;

    @ApiModelProperty(value = "内容")
    private String content;

    @ApiModelProperty(value = "评论ID")
    private String commentId;

    @ApiModelProperty(value = "父评论id，如果为顶级则为null")
    private Long pid;

    @ApiModelProperty(value = "姓名")
    private String nickName;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+8")
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "回复人姓名")
    private String replyName;

    @ApiModelProperty(value = "子评论")
    private List<CommentVo> child;
}
