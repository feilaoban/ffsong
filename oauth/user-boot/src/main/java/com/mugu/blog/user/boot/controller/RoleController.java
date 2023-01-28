package com.mugu.blog.user.boot.controller;

import com.mugu.blog.core.model.BaseParam;
import com.mugu.blog.core.model.PageData;
import com.mugu.blog.core.model.ResultMsg;
import com.mugu.blog.user.boot.service.SysRoleService;
import com.mugu.blog.user.common.req.SysRoleAddReq;
import com.mugu.blog.user.common.vo.SysRoleVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@Api(value = "角色接口")
@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private SysRoleService sysRoleService;

    @ApiOperation(value = "添加角色")
    @PostMapping("/add")
    public ResultMsg<Void> add(@RequestBody @Valid SysRoleAddReq param){
        sysRoleService.add(param);
        return ResultMsg.resultSuccess();
    }

    @ApiOperation(value = "获取角色列表")
    @PostMapping("/list")
    public ResultMsg<PageData<List<SysRoleVo>>> list(@RequestBody BaseParam param){
        return ResultMsg.resultSuccess(sysRoleService.list(param));
    }
}
