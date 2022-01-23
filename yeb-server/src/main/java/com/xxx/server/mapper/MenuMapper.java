package com.xxx.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xxx.server.pojo.Menu;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zhoubin
 * @since 2022-01-14
 */
public interface MenuMapper extends BaseMapper<Menu> {

    /**
     * 通过用户id获取菜单权限
     */
    List<Menu> getMenusByAdminId(Integer id);
}
