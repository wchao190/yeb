package com.xxx.server.controller;


import com.xxx.server.pojo.Department;
import com.xxx.server.pojo.ResultOV;
import com.xxx.server.service.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/system/basic/department")
public class DepartmentController {

    @Autowired
    private IDepartmentService departmentService;
    /**
     * 获取所有部门
     */
    @GetMapping("/")
    public List<Department> getDepartments(){
        return departmentService.getDepartments();
    }
    /**
     * 添加部门
     */
    @PostMapping("/")
    public ResultOV addDepartment(@RequestBody Department department){
        return departmentService.addDepartment(department);
    }
    /**
     * 删除部门
     */
    @DeleteMapping("/{id}")
    public  ResultOV deleteDepartment(@PathVariable("id") Integer id){
        return departmentService.deleteDepartment(id);
    }

}
