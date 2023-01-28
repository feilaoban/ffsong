package com.mugu.blog.user.boot.service;

import com.mugu.blog.core.model.BaseParam;
import com.mugu.blog.core.model.PageData;
import com.mugu.blog.user.common.req.SysRoleAddReq;
import com.mugu.blog.user.common.vo.SysRoleVo;

import java.util.List;

public interface SysRoleService {
    void add(SysRoleAddReq param);
    PageData<List<SysRoleVo>> list(BaseParam param);
}
