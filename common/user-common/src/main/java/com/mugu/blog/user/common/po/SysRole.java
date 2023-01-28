package com.mugu.blog.user.common.po;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 角色实体类
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SysRole {
    private Long id;

    private String name;

    private String code;

    private Integer status;

    @JsonIgnore
    private Date createTime;

    @JsonIgnore
    private Date updateTime;
}
