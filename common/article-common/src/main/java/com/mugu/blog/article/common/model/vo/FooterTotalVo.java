package com.mugu.blog.article.common.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class FooterTotalVo {

    @ApiModelProperty(value = "文章总数")
    private Long articleNum;

    @ApiModelProperty(value = "评论总数")
    private Long commentNum;

    @ApiModelProperty(value = "留言总数")
    private Long messageNum;

    @ApiModelProperty(value = "访问总数")
    private Long visitNum;

    @ApiModelProperty(value = "今日在线访问人数")
    private Long todayVisitNum;
}
