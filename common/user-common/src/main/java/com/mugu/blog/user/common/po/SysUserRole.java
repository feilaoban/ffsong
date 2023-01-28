package com.mugu.blog.user.common.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SysUserRole {
    private Long id;

    private Long userId;

    private Long roleId;
}
