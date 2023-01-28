package com.mugu.blog.article.common.model.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class ArticleAddReq {
    /**
     * 分类id
     */
    @NotNull(message = "分类不能为空")
    @ApiModelProperty(value ="分类id",required = true)
    private Long typeId;

    @NotBlank
    @ApiModelProperty(value = "摘要-描述",required = true)
    private String describe;

    /**
     * 标题
     */
    @NotBlank(message = "标题不能为空")
    @ApiModelProperty(value ="标题",required = true)
    private String title;

    /**
     * 内容
     */
    @NotBlank(message = "内容不能为空")
    @ApiModelProperty(value ="内容",required = true)
    private String content;

    /**
     * 来源：1 原创 2 转载 3 翻译
     */
    @NotNull(message = "来源不能为空")
    @ApiModelProperty(value ="来源：1 原创 2 转载 3 翻译",required = true)
    private Integer source;

    /**
     * 文章首图链接
     */
    @NotBlank(message = "文章首图链接不能为空")
    @ApiModelProperty(value ="文章首图链接",required = true)
    private String imgUrl;

    /**
     * 是否推荐 1 推荐 0 不推荐
     */
    @NotNull(message = "是否推荐不能为空")
    @ApiModelProperty(value ="id",required = true)
    private Integer isRecommend;

    /**
     * 转载说明 1 说明 0 不说明
     */
    @NotNull
    @ApiModelProperty(value ="转载说明 1 说明 0 不说明",required = true)
    private Integer isExplain;

    /**
     * 是否赞赏
     */
    @NotNull
    @ApiModelProperty(value ="是否赞赏",required = true)
    private Integer isAppreciate;

    /**
     * 是否评论
     */
    @NotNull
    @ApiModelProperty(value="是否评论",required = true)
    private Integer isComment;

    /**
     * 状态 1 已保存 2 已发布 0 删除
     */
    @NotNull
    @ApiModelProperty(value ="状态 1 已保存 2 已发布 0 删除",required = true)
    private Integer status;
}
