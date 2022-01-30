package com.xxx.server.config.security;

import com.xxx.server.pojo.Menu;
import com.xxx.server.pojo.Role;
import com.xxx.server.service.impl.MenuServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.IntFunction;

/**
 * 根据请求的 url 分析请求所需的角色，认证数据源
 * 根据用户传来的请求地址，分析请求需要的角色，并将所需要的角色放在 Collection中；
 */
@Component
public class CustomeFilter implements FilterInvocationSecurityMetadataSource {
    @Autowired
    private MenuServiceImpl menuService;
    // AntPathMatcher 是一个正则匹配工具
    AntPathMatcher antPathMatcher = new AntPathMatcher();
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        //获取请求的url
        String requestUrl = ((FilterInvocation) object).getRequestUrl();
        //根据角色获取菜单列表
        List<Menu> menus = menuService.getMenusWithRole();
        for (Menu menu: menus ){
            //获取当前请求 url 所需的角色
            if(antPathMatcher.match(menu.getUrl(),requestUrl)){
                //String[] strings = menu.getRoles().stream().map(Role::getName).toArray(String[] :: new);
//                List<Role> roles = menu.getRoles();
//                String[] strings = new String[roles.size()];
//                for(int i=0;i<roles.size();i++){
//                    strings[i] = roles.get(i).getName();
//                }
                //菜单对应的角色是 一对多 关系
                List<Role> roles = menu.getRoles();
                List<String> strings = new ArrayList<>();
                for (Role role:roles) {
                    strings.add(role.getName());
                }
                String[] str = new String[roles.size()];
                return org.springframework.security.access.SecurityConfig.createList(strings.toArray(str));
            }
        }
        return SecurityConfig.createList("ROLE_LOGIN");
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }
}
