package com.xxx.server.controller;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.xxx.server.pojo.Employee;
import com.xxx.server.pojo.RespPageBean;
import com.xxx.server.pojo.ResultOV;
import com.xxx.server.pojo.Salary;
import com.xxx.server.service.IEmployeeEcService;
import com.xxx.server.service.IEmployeeService;
import com.xxx.server.service.ISalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 员工账套
 */
@RestController
@RequestMapping("/salary/sobcnf")
public class SalarySobCfgController {

    @Autowired
    private ISalaryService salaryService;

    @Autowired
    private IEmployeeEcService employeeEcService;
    @Autowired
    private IEmployeeService service;
    /**
     * 查询所有工资账套
     */
    @GetMapping("/salaries")
    public List<Salary> getSalary(){
        return salaryService.list();
    }
    /**
     * 获取员工工资账套
     */
    @GetMapping("/")
    public RespPageBean getEmployeeWithSalary(@RequestParam(defaultValue = "1") Integer currentPage,
                                              @RequestParam(defaultValue = "20") Integer size){
        return employeeEcService.getEmployeeWithSalary(currentPage,size);
    }
    /**
     * 跟新员工账套
     */
    @PutMapping("/")
    public ResultOV updateEmployeeSalary(Integer eid,Integer sid){
        if(service.update(new UpdateWrapper<Employee>().set("salaryId",sid).eq("id",eid))){
            return ResultOV.success("更新成功!");
        }
        return ResultOV.error("更新失败!");
    }
}
