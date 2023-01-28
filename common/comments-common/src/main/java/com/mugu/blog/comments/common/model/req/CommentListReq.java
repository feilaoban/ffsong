package com.mugu.blog.comments.common.model.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CommentListReq {
    @ApiModelProperty(value = "文章ID，获取全部评论总数可不填")
    private String articleId;
}
