package com.mugu.blog.user.boot.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.mugu.blog.core.model.BaseParam;
import com.mugu.blog.core.model.PageData;
import com.mugu.blog.mybatis.config.utils.PageUtils;
import com.mugu.blog.user.boot.dao.SysRoleMapper;
import com.mugu.blog.user.boot.service.SysRoleService;
import com.mugu.blog.user.common.po.SysRole;
import com.mugu.blog.user.common.req.SysRoleAddReq;
import com.mugu.blog.user.common.vo.SysRoleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class SysRoleServiceImpl implements SysRoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Transactional
    @Override
    public void add(SysRoleAddReq param) {
        SysRole sysRole = new SysRole();
        BeanUtil.copyProperties(param,sysRole);
        sysRole.setCreateTime(new Date());
        sysRole.setUpdateTime(new Date());
        sysRole.setStatus(1);
        sysRoleMapper.insert(sysRole);
    }

    @Override
    public PageData<List<SysRoleVo>> list(BaseParam param) {
        return PageUtils.getPage(param, param1 -> sysRoleMapper.pageList(param));
    }
}
