package com.mugu.blog.user.boot.init;

import cn.hutool.core.collection.CollectionUtil;
import com.mugu.blog.core.model.oauth.OAuthConstant;
import com.mugu.blog.user.boot.service.SysPermissionService;
import com.mugu.blog.user.common.po.SysRole;
import com.mugu.blog.user.common.vo.SysRolePermissionVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LoadRolePermissionService {
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Autowired
    private SysPermissionService permissionService;

    /**
     * 初始化权限<->角色对应关系，将其放置到Redis中
     * 注：@PostConstruct注解的方法在项目启动的时候执行这个方法，也可以理解为在spring容器启动的时候执行，可作为一些数据的常规化加载，比如数据字典之类的。
     * Constructor >> @Autowired >> @PostConstruct
     */
    @PostConstruct
    public void init(){
        //获取所有的权限
        List<SysRolePermissionVo> list = permissionService.listRolePermission();
        list.parallelStream().peek(k->{
            List<String> roles=new ArrayList<>();
            if (CollectionUtil.isNotEmpty(k.getRoles())){
                for (SysRole role : k.getRoles()) {
                    roles.add(OAuthConstant.ROLE_PREFIX+role.getCode());
                }
            }
            //放入Redis中
            redisTemplate.opsForHash().put(OAuthConstant.OAUTH_URLS,k.getUrl(), roles);
        }).collect(Collectors.toList());
    }

}