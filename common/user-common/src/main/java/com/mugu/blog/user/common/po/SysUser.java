package com.mugu.blog.user.common.po;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 用户实体类
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SysUser {
    private Long id;

    private String userId;

    private String username;

    private String nickname;

    private Integer gender;

    private String avatar;

    private String password;

    private String mobile;

    private String email;

    private Integer status;

    @JsonIgnore
    private Date createTime;

    @JsonIgnore
    private Date updateTime;
}
