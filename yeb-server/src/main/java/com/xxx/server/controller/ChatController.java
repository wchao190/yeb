package com.xxx.server.controller;

import com.xxx.server.pojo.Admin;
import com.xxx.server.service.impl.EmployeeEcServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 在线聊天列表
 */
@RestController
@RequestMapping("/chat")
public class ChatController {
    @Autowired
    private EmployeeEcServiceImpl.IAdminService adminService;

    @GetMapping("/admin")
    public List<Admin> getAdmins(String keywords){
        return adminService.getAdmins(keywords);
    }
}
