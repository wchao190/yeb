package com.xxx.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xxx.server.pojo.Employee;
import com.xxx.server.pojo.EmployeeEc;
import com.xxx.server.pojo.RespPageBean;
import com.xxx.server.pojo.ResultOV;

import java.time.LocalDate;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhoubin
 * @since 2022-01-14
 */
public interface IEmployeeEcService extends IService<EmployeeEc> {
    /**
     * 查询所有员工
     * @param currentPage
     * @param size
     * @param employee
     * @param beginDateScope
     * @return
     */
    RespPageBean getEmployees(Integer currentPage, Integer size, Employee employee, LocalDate[] beginDateScope);

    /**
     * 获取最大工号
     * @return
     */
    ResultOV getWorkId();

    /**
     * 添加员工
     * @param employee
     * @return
     */
    ResultOV addEmployee(Employee employee);

    /**
     * 下载查出的员工
     */
    List<Employee> getEmployee(Integer id);

    /**
     * 获取员工工资账套
     * @param currentPage
     * @param size
     * @return
     */
    RespPageBean getEmployeeWithSalary(Integer currentPage, Integer size);
}
