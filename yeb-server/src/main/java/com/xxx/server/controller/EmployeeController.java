package com.xxx.server.controller;


import com.xxx.server.pojo.*;
import com.xxx.server.service.IEmployeeEcService;
import com.xxx.server.service.impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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
@RequestMapping("/employee/basic")
public class EmployeeController {
    @Autowired
    private IEmployeeEcService employeeEcService;
    @Autowired
    private NationServiceImpl nationService;
    @Autowired
    private PoliticsStatusServiceImpl politicsStatusService;
    @Autowired
    private JoblevelServiceImpl joblevelService;
    @Autowired
    private PositionServiceImpl positionService;
    @Autowired
    private DepartmentServiceImpl departmentService;
    @GetMapping("/")
    public RespPageBean getEmployees(@RequestParam(defaultValue = "1")Integer currentPage,
                                     @RequestParam(defaultValue = "20")Integer size,
                                     Employee employee,
                                     LocalDate[] beginDateScope){
        return employeeEcService.getEmployees(currentPage,size,employee,beginDateScope);

    }
    /**
     * 获取所有名族
     */
    @GetMapping("/nations")
    public List<Nation> getNations(){
        return nationService.list();
    }
    /**
     * 获取所有政治面貌
     */
    @GetMapping("/policicsstatus")
    public List<PoliticsStatus> getPoliticsStatus(){
        return politicsStatusService.list();
    }
    /**
     * 获取所有职称
     */
    @GetMapping("/joblevels")
    public List<Joblevel> getJoblevels(){
        return joblevelService.list();
    }
    /**
     * 获取所有职位
     */
    @GetMapping("/positions")
    public List<Position> getPositions(){
        return positionService.list();
    }
    /**
     * 获取所有工号
     */
    @GetMapping("/maxWorkId")
    public ResultOV getWorkId(){
        return employeeEcService.getWorkId();
    }
    /**
     * 获取部门id
     */
    @GetMapping("/deps")
    public List<Department> getDepartments(){
        return departmentService.getDepartments();
    }
    /**
     * 添加员工
     */
    @PostMapping("/")
    public ResultOV addEmployee(@RequestBody Employee employee){
        return employeeEcService.addEmployee(employee);
    }
}
