package com.xxx.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xxx.server.mapper.MenuRoleMapper;
import com.xxx.server.pojo.MenuRole;
import com.xxx.server.pojo.ResultOV;
import com.xxx.server.service.IMenuRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhoubin
 * @since 2022-01-14
 */
@Service
public class MenuRoleServiceImpl extends ServiceImpl<MenuRoleMapper, MenuRole> implements IMenuRoleService {
    @Autowired
    private MenuRoleMapper menuRoleMapper;
    /**
     * 更新角色对应的菜单，先删除原来的菜单，再更新
     * @param rid
     * @param mids
     * @return
     */
    @Override
    @Transactional
    public ResultOV updateMenuRole(Integer rid, Integer[] mids) {
        menuRoleMapper.delete(new QueryWrapper<MenuRole>().eq("rid",rid));
        if(mids == null || mids.length ==0){
            return ResultOV.success("更新成功!");
        }
        Integer integer = menuRoleMapper.insertRecord(rid, mids);
        if(integer == mids.length){
            ResultOV.success("更新成功"+ integer+"条!");
        }
        return ResultOV.success("更新失败!");
    }
}
