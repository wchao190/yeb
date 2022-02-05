package com.xxx.server.pojo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

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
@RequiredArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false,of = "name")
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
    @Excel(name="部门")
    @NonNull
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
