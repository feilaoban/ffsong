package com.mugu.blog.user.common.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SysRolePermission {
    private Long id;

    private Long roleId;

    private Long permissionId;
}
