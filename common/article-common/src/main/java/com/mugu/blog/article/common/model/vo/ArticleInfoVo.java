package com.mugu.blog.article.common.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ArticleInfoVo extends ArticleVo {
    @ApiModelProperty(value = "文章内容")
    private String content;

    //TODO 留言
}
