package com.mugu.blog.comments.common.model.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class CommentAddReq {

    @ApiModelProperty(value = "父评论ID，顶层评论不填")
    private String pid;

    @ApiModelProperty(value = "文章ID",required = true)
    @NotNull(message = "文章ID不能为空")
    private Long articleId;

    @ApiModelProperty(value = "姓名",required = true)
    @NotBlank(message = "姓名不能为空")
    private String nickName;

    @ApiModelProperty(value = "邮箱",required = true)
    @NotBlank(message = "邮箱不能为空")
    @Email(message = "邮件格式不正确！")
    private String email;

    @ApiModelProperty(value = "内容",required = true)
    @NotBlank(message = "评论不能为空")
    private String content;
}
