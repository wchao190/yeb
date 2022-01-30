package com.xxx.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xxx.server.mapper.MenuMapper;
import com.xxx.server.pojo.Admin;
import com.xxx.server.pojo.Menu;
import com.xxx.server.pojo.Role;
import com.xxx.server.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhoubin
 * @since 2022-01-14
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private RedisTemplate redisTemplate;
    /**
     * 根据用户id 查询菜单
     * @return
     */
    @Override
    public List<Menu> getMenusByAdminId() {
        Admin principal = (Admin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Integer id = principal.getId();
        ValueOperations ops = redisTemplate.opsForValue();
        List<Menu> menus = (List<Menu>) ops.get("menu_" + id);
        //如果 redis 中没有数据，去数据库查
        if(CollectionUtils.isEmpty(menus)){
            menus = menuMapper.getMenusByAdminId(id);
            ops.set("menu_"+id,menus);
        }
        return menus;
    }
    /**
     * 通过角色获取菜单列表
     */
    public List<Menu> getMenusWithRole(){
        return menuMapper.getMenusWithRole();
    }
}
