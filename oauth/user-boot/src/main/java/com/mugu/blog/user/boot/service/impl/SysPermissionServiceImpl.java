package com.mugu.blog.user.boot.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.mugu.blog.user.boot.dao.SysPermissionMapper;
import com.mugu.blog.user.boot.dao.SysRoleMapper;
import com.mugu.blog.user.boot.service.SysPermissionService;
import com.mugu.blog.user.common.po.SysPermission;
import com.mugu.blog.user.common.po.SysRole;
import com.mugu.blog.user.common.vo.SysRolePermissionVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SysPermissionServiceImpl implements SysPermissionService {

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Autowired
    private SysPermissionMapper sysPermissionMapper;

    @Override
    public List<SysRolePermissionVo> listRolePermission() {
        //获取所有的权限集合
        List<SysPermission> permissions = sysPermissionMapper.list();
        //获取所有的权限对应的角色
        List<SysRolePermissionVo> list=new ArrayList<>();
        permissions.parallelStream().peek(p->{
            List<SysRole> roles = sysRoleMapper.listByPermissionId(p.getId());
            SysRolePermissionVo vo = new SysRolePermissionVo();
            BeanUtil.copyProperties(p,vo);
            vo.setRoles(roles);
            list.add(vo);
        }).collect(Collectors.toList());
        return list;
    }
}
