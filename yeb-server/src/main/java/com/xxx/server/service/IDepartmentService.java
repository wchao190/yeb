package com.xxx.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xxx.server.pojo.Department;
import com.xxx.server.pojo.ResultOV;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhoubin
 * @since 2022-01-14
 */
public interface IDepartmentService extends IService<Department> {
    /**
     * 获取所有部门
     * @return
     */
    List<Department> getDepartments();

    /**
     * 添加部门
     * @param department
     * @return
     */
    ResultOV addDepartment(Department department);

    /**
     * 删除部门
     * @param id
     * @return
     */
    ResultOV deleteDepartment(Integer id);
}
