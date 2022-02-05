package com.xxx.server.pojo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelEntity;
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
    @Excel(name="员工姓名")
    private String name;

    /**
     * 性别
     */
    @Excel(name="性别")
    private String gender;

    /**
     * 出生日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Excel(name="出生日期",width = 20,format = "yyyy-MM-dd")
    private LocalDate birthday;

    /**
     * 身份证号
     */
    @Excel(name="身份证号",width = 30)
    private String idCard;

    /**
     * 婚姻状况
     */
    @Excel(name="婚姻状态")
    private String wedlock;

    /**
     * 民族
     */
    private Integer nationId;

    /**
     * 籍贯
     */
    @Excel(name="籍贯")
    private String nativePlace;

    /**
     * 政治面貌
     */
    private Integer politicId;

    /**
     * 邮箱
     */
    @Excel(name="邮箱",width = 30)
    private String email;

    /**
     * 电话号码
     */
    @Excel(name="电话号码",width = 15)
    private String phone;

    /**
     * 联系地址
     */
    @Excel(name="联系地址",width = 40)
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
    @Excel(name="聘用形式")
    private String engageForm;

    /**
     * 最高学历
     */
    @Excel(name="最高学历")
    private String tiptopDegree;

    /**
     * 所属专业
     */
    @Excel(name="所属专业",width = 20)
    private String specialty;

    /**
     * 毕业院校
     */
    @Excel(name="毕业院校",width = 20)
    private String school;

    /**
     * 入职日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Excel(name="入职日期",width = 20,format = "yyyy-MM-dd")
    private LocalDate beginDate;

    /**
     * 在职状态
     */
    @Excel(name="在职状态")
    private String workState;

    /**
     * 工号
     */
    @Excel(name="工号")
    private String workID;

    /**
     * 合同期限
     */
    @Excel(name="合同期限",suffix = "年")
    private Double contractTerm;

    /**
     * 转正日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Excel(name="转正日期",width = 20)
    private LocalDate conversionTime;

    /**
     * 离职日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Excel(name="离职日期",width = 20)
    private LocalDate notWorkDate;

    /**
     * 合同起始日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Excel(name="合同起始日期",width = 20)
    private LocalDate beginContract;

    /**
     * 合同终止日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Excel(name="合同终止日期",width = 20)
    private LocalDate endContract;

    /**
     * 工龄
     */
    @Excel(name="工龄")
    private Integer workAge;

    /**
     * 工资账套ID
     */
    private Integer salaryId;
    /**
     * 名族
     */
    @TableField(exist=false)
    @ExcelEntity(name = "民族")
    private Nation nation;
    /**
     * 政治面貌
     */
    @TableField(exist = false)
    @ExcelEntity(name = "政治面貌")
    private  PoliticsStatus politicsStatus;
    /**
     * 部门
     */
    @TableField(exist = false)
    @ExcelEntity(name="部门")
    private  Department department;

    /**
     * 职称
     */
    @TableField(exist = false)
    @ExcelEntity(name="职称")
    private  Joblevel joblevel;

    /**
     * 职位
     */
    @TableField(exist = false)
    @ExcelEntity(name="职位")
    private  Position position;
}
