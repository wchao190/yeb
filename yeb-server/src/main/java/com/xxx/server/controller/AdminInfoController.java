package com.xxx.server.controller;

import com.xxx.server.pojo.Admin;
import com.xxx.server.pojo.ResultOV;
import com.xxx.server.service.impl.AdminServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 个人中心
 */
@RestController
public class AdminInfoController {
    @Autowired
    private AdminServiceImpl adminService;

    @PutMapping("/admin/info")
    public ResultOV updateAdmin(@RequestBody Admin admin, Authentication authentication){
        if (adminService.updateById(admin)){
            //每次更新都要重新设置 Authentication
            SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(admin,null,authentication.getAuthorities()));
            return ResultOV.success("更新成功!");
        }
        return ResultOV.error("更新失败!");
    }

    @PutMapping("/admin/pass")
    public ResultOV updateAdminPassword(@RequestBody Map<String,Object> info){
        String oldPass = (String)info.get("oldPass");
        String pass = (String)info.get("pass");
        Integer adminId = (Integer)info.get("adminId");
        return adminService.updateAdminPassword(oldPass,pass,adminId);
    }
}
