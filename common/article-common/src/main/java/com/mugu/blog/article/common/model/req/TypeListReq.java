package com.mugu.blog.article.common.model.req;

import com.mugu.blog.core.model.BaseParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class TypeListReq extends BaseParam {
    @ApiModelProperty(value = "分类名称")
    private String name;
}
