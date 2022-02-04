package com.xxx.server.controller;


import com.xxx.server.pojo.Admin;
import com.xxx.server.service.impl.AdminServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zhoubin
 * @since 2022-01-14
 */
@RestController
@RequestMapping("/system/admin")
public class AdminController {
    @Autowired
    private AdminServiceImpl adminService;

    /**
     * 获取所有操作员
     * @param keywords
     * @return
     */
    @GetMapping("/")
    public List<Admin> getAdmins(String keywords){
        return adminService.getAdmins(keywords);
    }

}
