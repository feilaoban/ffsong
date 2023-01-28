package com.mugu.blog.user.boot.service;

import com.mugu.blog.user.common.vo.SysRolePermissionVo;

import java.util.List;

public interface SysPermissionService {
    List<SysRolePermissionVo> listRolePermission();
}
