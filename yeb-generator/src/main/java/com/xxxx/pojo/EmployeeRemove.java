package com.xxxx.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

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
@TableName("t_employee_remove")
public class EmployeeRemove implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 员工id
     */
    private Integer eid;

    /**
     * 调动后部门
     */
    private Integer afterDepId;

    /**
     * 调动后职位
     */
    private Integer afterJobId;

    /**
     * 调动日期
     */
    private LocalDate removeDate;

    /**
     * 调动原因
     */
    private String reason;

    /**
     * 备注
     */
    private String remark;


}
