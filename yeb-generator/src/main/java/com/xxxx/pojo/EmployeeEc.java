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
@TableName("t_employee_ec")
public class EmployeeEc implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 员工编号
     */
    private Integer eid;

    /**
     * 奖罚日期
     */
    private LocalDate ecDate;

    /**
     * 奖罚原因
     */
    private String ecReason;

    /**
     * 奖罚分
     */
    private Integer ecPoint;

    /**
     * 奖罚类别，0：奖，1：罚
     */
    private Integer ecType;

    /**
     * 备注
     */
    private String remark;


}
