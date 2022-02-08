package com.xxx.server.controller;


import com.xxx.server.pojo.ResultOV;
import com.xxx.server.pojo.Salary;
import com.xxx.server.service.ISalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
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
@RequestMapping("/salary/sob")
public class SalaryController {
    @Autowired
    private ISalaryService salaryService;

    /**
     * 查询工资套账
     * @return
     */
    @GetMapping("/")
    public List<Salary> getSalary(){
        return salaryService.list();
    }
    /**
     * 添加工资套账
     */
    @PostMapping("/")
    public ResultOV addSalary(@RequestBody Salary salary) {
        salary.setCreateDate(LocalDateTime.now());
        if(salaryService.save(salary)){
            return ResultOV.success("添加成功!");
        }
        return ResultOV.error("添加失败!");
    }
    /**
     * 删除工资套账
     */
    @DeleteMapping("/{id}")
    public ResultOV deleteSalary(@PathVariable("id") Integer id){
        if(salaryService.removeById(id)){
            return ResultOV.success("删除成功!");
        }
        return ResultOV.error("删除失败!");
    }
    /**
     * 更新工资套账
     */
    @PutMapping("/")
    public ResultOV updateSalary(@RequestBody Salary salary){
        if(salaryService.updateById(salary)){
            return ResultOV.success("更新成功!");
        }
        return ResultOV.error("更新失败!");
    }
}
