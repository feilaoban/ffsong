package com.mugu.blog.user.boot.controller;

import com.mugu.blog.core.model.ResultMsg;
import com.mugu.blog.user.common.req.SysPermissionAddReq;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Api(value = "接口权限")
@RequestMapping("/permission")
@RestController
public class PermissionController {

//    @PostMapping("/add")
//    public ResultMsg<Void> add(@RequestBody @Valid SysPermissionAddReq param){
//
//    }
}
