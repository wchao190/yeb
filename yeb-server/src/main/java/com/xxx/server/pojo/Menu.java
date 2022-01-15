package com.xxx.server.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author zhoubin
 * @since 2022-01-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_menu")
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * url
     */
    private String url;

    /**
     * path
     */
    private String path;

    /**
     * 组件
     */
    private String component;

    /**
     * 菜单名
     */
    private String name;

    /**
     * 图标
     */
    private String iconCls;

    /**
     * 是否保持激活
     */
    private Boolean keepAlive;

    /**
     * 是否要求权限
     */
    private Boolean requireAuth;

    /**
     * 父id
     */
    private Integer parentId;

    /**
     * 是否启用
     */
    private Boolean enabled;


}
