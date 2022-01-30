package com.xxx.server.controller;

import com.xxx.server.pojo.Admin;
import com.xxx.server.pojo.AdminLoginParam;
import com.xxx.server.pojo.ResultOV;
import com.xxx.server.service.impl.EmployeeEcServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@RestController
public class LoginController {

    @Autowired
    private EmployeeEcServiceImpl.IAdminService adminService;

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public ResultOV login(@RequestBody AdminLoginParam adminLoginParam, HttpServletRequest request){
        return adminService.login(adminLoginParam.getUsername(),adminLoginParam.getPassword(),adminLoginParam.getCode(),request);
    }

    /**
     * 获取当前登录的用户信息
     * @return
     */
    @GetMapping("/admin/info")
    public Admin getAdminInfo(Principal principal){
        if(principal == null){
            return null;
        }
        String name = principal.getName();
        Admin admin = adminService.getAdminByUserName(name);
        admin.setPassword(null);
        //获取用户角色
        admin.setRole(adminService.getRoles(admin.getId()));
        return admin;
    }

    /**
     * 退出
     * @return
     */
    @PostMapping("/logout")
    public ResultOV logout(){
        return ResultOV.success("注销成功");
    }
}
