package com.mugu.blog.user.boot.dao;

import com.mugu.blog.core.model.BaseParam;
import com.mugu.blog.user.common.po.SysPermission;
import com.mugu.blog.user.common.po.SysRole;
import com.mugu.blog.user.common.vo.SysRoleVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysRoleMapper {

    /**
    * 新增
    **/
    int insert(SysRole sysRole);

    /**
    * 刪除
    **/
    int delete(int id);

    /**
    * 更新
    **/
    int update(SysRole sysRole);

    /**
    * 查询 根据主键 id 查询
    **/
    SysRole load(int id);

    /**
    * 查询 分页查询
    **/
    List<SysRoleVo> pageList(BaseParam param);

    /**
    * 查询 分页查询 count
    **/
    int pageListCount(int offset,int pagesize);

    List<SysRole> listByPermissionId(Long id);
}