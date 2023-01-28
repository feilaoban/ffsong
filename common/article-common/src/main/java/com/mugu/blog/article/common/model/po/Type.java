package com.mugu.blog.article.common.model.po;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
public class Type {
    private Long id;

    @ApiModelProperty(value = "分类名称")
    @NotBlank(message = "分类名称不能为空！")
    private String name;

    @JsonIgnore
    private Date createTime;

    @JsonIgnore
    private Date updateTime;
}
