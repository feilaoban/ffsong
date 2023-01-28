package com.mugu.blog.article.common.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class TypeVo {

    @ApiModelProperty(value = "分类ID")
    private Long id;

    @ApiModelProperty(value = "文章总数")
    private Long total;

    @ApiModelProperty(value = "分类名称")
    private String name;
}
