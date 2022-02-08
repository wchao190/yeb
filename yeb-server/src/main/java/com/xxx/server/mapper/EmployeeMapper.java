package com.xxx.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xxx.server.pojo.Employee;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zhoubin
 * @since 2022-01-14
 */
public interface EmployeeMapper extends BaseMapper<Employee> {
    /**
     * 获取所有员工
     * @param page
     * @param employee
     * @param beginDateScope
     */
    IPage<Employee> getEmployees(Page<Employee> page, @Param("employee") Employee employee,
                                 @Param("beginDateScope") LocalDate[] beginDateScope);

    /**
     * 下载查出的员工
     * @param id
     * @return
     */
    List<Employee> getEmployee(Integer id);

    /**
     * 员工工资账套
     * @param page
     * @return
     */
    IPage<Employee> getEmployeeWithSalary(Page<Employee> page);
}
