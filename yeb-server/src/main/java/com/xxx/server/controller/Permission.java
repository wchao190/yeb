package com.xxx.server.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xxx.server.pojo.Menu;
import com.xxx.server.pojo.MenuRole;
import com.xxx.server.pojo.ResultOV;
import com.xxx.server.pojo.Role;
import com.xxx.server.service.IMenuRoleService;
import com.xxx.server.service.IMenuService;
import com.xxx.server.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.ParameterResolutionDelegate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 权限设置
 */
@RestController
@RequestMapping("/system/basic/permission")
public class Permission {
    @Autowired
    private IRoleService roleService;
    @Autowired
    private IMenuService menuService;
    @Autowired
    private IMenuRoleService menuRoleService;
    /**
     * 查询所有角色
     */
    @GetMapping("/")
    public List<Role> getRoles(){
        return roleService.list();
    }
    /**
     * 添加角色
     */
    @PostMapping("/")
    public ResultOV addRoles(@RequestBody Role role){
        if(!role.getName().startsWith("ROLE_")){
            role.setName("ROLE_"+role.getName());
        }
        if(roleService.save(role)){
            return ResultOV.success("添加成功!");
        }
        return ResultOV.error("添加失败!");
    }
    /**
     * 删除角色
     */
    @DeleteMapping("/role/{id}")
    public ResultOV deleteRole(@PathVariable("id") Integer id){
        if(roleService.removeById(id)){
            return ResultOV.success("删除成功!");
        }
        return ResultOV.error("删除失败!");
    }
    /**
     * 查询所有菜单
     */
    @GetMapping("/menus")
    public List<Menu> getMenus(){
        return menuService.getMenus();
    }
    /**
     * 根据角色id 查询菜单id
     */
    @GetMapping("/mid/{rid}")
    public List<Integer> getMidByRid(@PathVariable Integer rid){
        return menuRoleService.list(new QueryWrapper<MenuRole>().eq("rid", rid)).stream()
                .map(MenuRole::getMid)
                .collect(Collectors.toList());
    }
    /**
     * 更新角色对应的菜单
     */
    @PutMapping("/")
    public ResultOV updateMenuRole(Integer rid,Integer[] mids){
        return menuRoleService.updateMenuRole(rid,mids);
    }
}
