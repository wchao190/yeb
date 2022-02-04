package com.xxx.server.controller;


import com.xxx.server.pojo.Admin;
import com.xxx.server.pojo.ResultOV;
import com.xxx.server.service.INationService;
import com.xxx.server.service.impl.AdminServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.web.bind.annotation.*;

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
    /**
     * 更新操作员
     */
    @PutMapping("/")
    public ResultOV updateAdmin(@RequestBody Admin admin){

        if(adminService.updateById(admin)){
            return ResultOV.success("跟新成功!");
        }
        return ResultOV.error("更新失败!");
    }
    /**
     * 删除操作员
     */
    @DeleteMapping("/{id}")
    public ResultOV deleteAdmin(@PathVariable("id") Integer id){
        if(adminService.removeById(id)){
            return ResultOV.success("删除成功!");
        }
        return ResultOV.error("删除失败!");
    }
}
