package com.xxx.server.service.impl;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xxx.server.mapper.EmployeeEcMapper;
import com.xxx.server.pojo.*;
import com.xxx.server.service.IEmployeeEcService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
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
public class EmployeeEcServiceImpl extends ServiceImpl<EmployeeEcMapper, EmployeeEc> implements IEmployeeEcService {

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
