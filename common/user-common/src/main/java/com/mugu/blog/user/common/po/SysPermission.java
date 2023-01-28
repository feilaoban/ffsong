package com.mugu.blog.user.common.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 权限实体类
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SysPermission {
    private Long id;

    private String name;

    private String url;

    private Date createTime;

    private Date updateTime;
}
