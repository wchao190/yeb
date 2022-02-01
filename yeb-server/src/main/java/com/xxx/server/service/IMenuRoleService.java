package com.xxx.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xxx.server.pojo.MenuRole;
import com.xxx.server.pojo.ResultOV;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhoubin
 * @since 2022-01-14
 */
public interface IMenuRoleService extends IService<MenuRole> {
    /**
     * 更新角色对应的菜单
     * @param rid
     * @param mids
     * @return
     */
    ResultOV updateMenuRole(Integer rid, Integer[] mids);

}
