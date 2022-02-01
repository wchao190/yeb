package com.xxx.server.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

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
@TableName("t_department")
public class Department implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 部门名称
     */
    private String name;

    /**
     * 父id
     */
    private Integer parentId;

    /**
     * 路径
     */
    private String depPath;

    /**
     * 是否启用
     */
    private Boolean enabled;

    /**
     * 是否上级
     */
    private Boolean isParent;

    /**
     * 子部门
     */
    @TableField(exist = false)
    private List<Department> children;

    /**
     * 接收存储过程返回的结果
     */
    @TableField(exist = false)
    private  Integer result;
}
