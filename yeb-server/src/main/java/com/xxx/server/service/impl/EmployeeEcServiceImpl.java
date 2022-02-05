package com.xxx.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xxx.server.mapper.EmployeeEcMapper;
import com.xxx.server.mapper.EmployeeMapper;
import com.xxx.server.pojo.*;
import com.xxx.server.service.IEmployeeEcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhoubin
 * @since 2022-01-14
 */
@Service
public class EmployeeEcServiceImpl extends ServiceImpl<EmployeeEcMapper, EmployeeEc> implements IEmployeeEcService {

    @Autowired
    private EmployeeMapper employeeMapper;
    /**
     * 获取所有员工
     * @param currentPage
     * @param size
     * @param employee
     * @param beginDateScope
     * @return
     */
    @Override
    public RespPageBean getEmployees(Integer currentPage, Integer size, Employee employee,
                                     LocalDate[] beginDateScope) {
        //开启分页
        Page<Employee> page = new Page<>(currentPage,size);
        IPage<Employee> employees = employeeMapper.getEmployees(page, employee, beginDateScope);
        RespPageBean respPageBean = new RespPageBean(employees.getTotal(),employees.getRecords());
        return respPageBean;
    }

    /**
     * 获取最大工号
     * @return
     */
    @Override
    public ResultOV getWorkId() {
        List<Map<String, Object>> maps = employeeMapper.selectMaps(new QueryWrapper<Employee>().select("max(workId)"));
        return ResultOV.success(
                String.format("%08d",Integer.parseInt(maps.get(0).get("max(workId)").toString()+1))
        );
    }

    /**
     * 添加员工
     * @param employee
     * @return
     */
    @Override
    public ResultOV addEmployee(Employee employee) {
        //计算合同期限
        LocalDate beginContract = employee.getBeginContract();
        LocalDate endContract = employee.getEndContract();
        //合同起止天数
        long days = beginContract.until(endContract, ChronoUnit.DAYS);
        DecimalFormat format = new DecimalFormat("##.00");
        //保留2位小数
        employee.setContractTerm(Double.parseDouble(format.format(days / 365)));
        if(employeeMapper.insert(employee) == 1){
            return ResultOV.success("添加成功!");
        }
        return ResultOV.error("添加失败!");
    }

    /**
     * <p>
     *  服务类
     * </p>
     *
     * @author zhoubin
     * @since 2022-01-14
     */
    public static interface IAdminService extends IService<Admin> {

        /**
         * 登录之后返回 token
         * @param username
         * @param password
         * @return
         */
        ResultOV login(String username, String password, String code, HttpServletRequest request);

        /**
         * 根据用户名获取用户
         * @param name
         * @return
         */
        Admin getAdminByUserName(String name);

        /**
         * 根据用户id获取用户角色
         * @param id
         * @return
         */
        List<Role> getRoles(Integer id);

        /**
         * 获取所有操作员
         * @param keywords
         * @return
         */
        List<Admin> getAdmins(String keywords);
    }
}
