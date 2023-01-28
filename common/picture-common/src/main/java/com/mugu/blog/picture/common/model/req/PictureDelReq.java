package com.mugu.blog.picture.common.model.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class PictureDelReq {
    @ApiModelProperty(value = "主键ID",required = true)
    @NotNull(message = "ID不能为空")
    private Long id;
}
