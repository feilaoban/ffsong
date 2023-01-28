package com.mugu.blog.article.common.model.req;

import com.mugu.blog.core.model.BaseParam;
import lombok.Data;

@Data
public class TypeDTO extends BaseParam {
    private Long id;
    private String name;
}
