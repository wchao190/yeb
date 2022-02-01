package com.xxx.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xxx.server.mapper.DepartmentMapper;
import com.xxx.server.pojo.Department;
import com.xxx.server.pojo.ResultOV;
import com.xxx.server.service.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhoubin
 * @since 2022-01-14
 */
@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements IDepartmentService {
    @Autowired
    private DepartmentMapper departmentMapper;
    /**
     * 获取所有部门
     * @return
     */
    @Override
    public List<Department> getDepartments() {
        return departmentMapper.getDepartments(-1);
    }

    /**
     * 添加部门
     * @param department
     * @return
     */
    @Override
    public ResultOV addDepartment(Department department) {
        department.setEnabled(true);
        departmentMapper.addDepartment(department);
        if(department.getResult() == 1){
            return ResultOV.success("添加成功!",department);
        }
        return ResultOV.success("添加失败!");
    }

    /**
     * 删除部门
     * @param id
     * @return
     */
    @Override
    public ResultOV deleteDepartment(Integer id) {
        Department department = new Department();
        department.setId(id);
        departmentMapper.deleteDepartment(department);
        if(department.getResult() == -2){
            return ResultOV.error("该部门还有子部门，删除失败!");
        }
        if(department.getResult() == -1){
            return ResultOV.error("该部门还有员工，删除失败!");
        }
        if(department.getResult() == 1){
            return ResultOV.error("删除成功!");
        }
        return ResultOV.error("删除失败!");
    }
}
