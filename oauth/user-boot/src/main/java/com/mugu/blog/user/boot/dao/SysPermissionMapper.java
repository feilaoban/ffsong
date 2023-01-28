package com.mugu.blog.user.boot.dao;

import com.mugu.blog.user.common.po.SysPermission;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @description 权限表
 */
@Mapper
public interface SysPermissionMapper {

    /**
    * 新增
    **/
    int insert(SysPermission sysPermission);

    /**
    * 刪除
    **/
    int delete(int id);

    /**
    * 更新
    **/
    int update(SysPermission sysPermission);

    /**
    * 查询 根据主键 id 查询
    **/
    SysPermission load(int id);

    /**
    * 查询 分页查询
    **/
    List<SysPermission> pageList(int offset, int pagesize);

    List<SysPermission> list();

    /**
    * 查询 分页查询 count
    **/
    int pageListCount(int offset,int pagesize);

}