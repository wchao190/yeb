package com.xxx.server.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;

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
@TableName("t_employee")
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 员工编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 员工姓名
     */
    private String name;

    /**
     * 性别
     */
    private String gender;

    /**
     * 出生日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;

    /**
     * 身份证号
     */
    private String idCard;

    /**
     * 婚姻状况
     */
    private String wedlock;

    /**
     * 民族
     */
    private Integer nationId;

    /**
     * 籍贯
     */
    private String nativePlace;

    /**
     * 政治面貌
     */
    private Integer politicId;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 电话号码
     */
    private String phone;

    /**
     * 联系地址
     */
    private String address;

    /**
     * 所属部门
     */
    private Integer departmentId;

    /**
     * 职称ID
     */
    private Integer jobLevelId;

    /**
     * 职位ID
     */
    private Integer posId;

    /**
     * 聘用形式
     */
    private String engageForm;

    /**
     * 最高学历
     */
    private String tiptopDegree;

    /**
     * 所属专业
     */
    private String specialty;

    /**
     * 毕业院校
     */
    private String school;

    /**
     * 入职日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate beginDate;

    /**
     * 在职状态
     */
    private String workState;

    /**
     * 工号
     */
    private String workID;

    /**
     * 合同期限
     */
    private Double contractTerm;

    /**
     * 转正日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate conversionTime;

    /**
     * 离职日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate notWorkDate;

    /**
     * 合同起始日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate beginContract;

    /**
     * 合同终止日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endContract;

    /**
     * 工龄
     */
    private Integer workAge;

    /**
     * 工资账套ID
     */
    private Integer salaryId;
    /**
     * 名族
     */
    @TableField(exist=false)
    private Nation nation;
    /**
     * 政治面貌
     */
    @TableField(exist = false)
    private  PoliticsStatus politicsStatus;
    /**
     * 部门
     */
    @TableField(exist = false)
    private  Department department;

    /**
     * 职称
     */
    @TableField(exist = false)
    private  Joblevel joblevel;

    /**
     * 职位
     */
    @TableField(exist = false)
    private  Position position;
}
