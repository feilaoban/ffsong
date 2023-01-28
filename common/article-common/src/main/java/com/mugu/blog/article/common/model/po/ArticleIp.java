package com.mugu.blog.article.common.model.po;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ArticleIp {

    @ApiModelProperty(value = "主键Id")
    private Long id;

    @ApiModelProperty(value = "文章ID")
    private Long articleId;

    @ApiModelProperty(value = "ip地址")
    private String ip;
}
