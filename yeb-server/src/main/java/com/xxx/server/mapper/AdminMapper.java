package com.xxx.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xxx.server.pojo.Admin;
import com.xxx.server.pojo.Menu;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zhoubin
 * @since 2022-01-14
 */
public interface AdminMapper extends BaseMapper<Admin> {
    /**
     * 获取所有操作员
     *
     * @param id
     * @param keywords
     * @return
     */
    List<Admin> getAdmins(Integer id, String keywords);
}
