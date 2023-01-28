package com.mugu.blog.article.common.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class ArticleVo {
    /**
     * id
     */
    @ApiModelProperty(value ="id")
    private Long id;

    @ApiModelProperty(value = "唯一ID")
    private String articleId;

    /**
     * 作者userid
     */
    @ApiModelProperty(value ="作者姓名")
    private String authorName;

    @ApiModelProperty(value = "作者头像")
    private String authorAvatar;

    @ApiModelProperty(value ="分类名称")
    private String typeName;

    @ApiModelProperty(value = "分类ID")
    private Long typeId;

    /**
     * 标题
     */
    @ApiModelProperty(value ="标题")
    private String title;

    /**
     * 来源：1 原创 2 转载 3 翻译
     */
    @ApiModelProperty(value ="来源：1 原创 2 转载 3 翻译")
    private Integer source;

    /**
     * 阅读人数
     */
    @ApiModelProperty(value ="阅读人数")
    private Long viewNum;

    @ApiModelProperty(value = "摘要-描述")
    private String describe;

    /**
     * 文章首图链接
     */
    @ApiModelProperty(value ="文章首图链接")
    private String imgUrl;

    /**
     * 是否推荐 1 推荐 0 不推荐
     */
    @ApiModelProperty(value ="id")
    private Integer isRecommend;

    /**
     * 转载说明 1 说明 0 不说明
     */
    @ApiModelProperty(value ="转载说明 1 说明 0 不说明")
    private Integer isExplain;

    /**
     * 是否赞赏
     */
    @ApiModelProperty(value ="是否赞赏")
    private Integer isAppreciate;

    /**
     * 是否评论
     */
    @ApiModelProperty(value ="是否评论")
    private Integer isComment;

    @ApiModelProperty(value = "评论总数")
    private Long commentNum;


    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "发布时间")
    private Date createTime;

    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    @ApiModelProperty(value = "状态 1 已保存 2 已发布 0 删除")
    private Integer status;
}
