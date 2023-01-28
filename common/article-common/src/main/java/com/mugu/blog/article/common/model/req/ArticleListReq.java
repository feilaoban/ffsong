package com.mugu.blog.article.common.model.req;
import com.mugu.blog.core.model.BaseParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

@Data
public class ArticleListReq extends BaseParam {
    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "分类ID")
    private Long typeId;

    @ApiModelProperty(value = "文章内容")
    private String content;

    @ApiModelProperty(value = "状态 1 草稿 2 已发布")
    @Range(min = 1,max = 2)
    private Integer status;

    @ApiModelProperty(value = "1 推荐 0 不推荐 不填全部")
    @Range(min = 0,max = 1)
    private Integer isRecommend;

}
