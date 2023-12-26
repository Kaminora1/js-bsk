package com.rd.bsk.pojo;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author liuhang
 * @since 2023-11-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_emp")
public class Emp implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 员工编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 登录账号
     */
    private String loginName;

    /**
     * 登录密码
     */
    private String loginPwd;

    /**
     * 真实姓名
     */
    private String empName;

    /**
     * 手机号码
     */
    private String empPhone;

    /**
     * 员工性别
     */
    private String empSex;

    /**
     * 员工身份证号码
     */
    private String empIdcard;

    /**
     * 员工工资
     */
    private BigDecimal empSalary;

    /**
     * 员工入职时间
     */
    private LocalDateTime empHiredate;

    /**
     * 员工状态 0在职，1离职
     */
    private Integer empStatus;

    /**
     * 员工角色 0普通员工 1管理员
     */
    private Integer empRole;

    /**
     * 员工备注信息
     */
    private String empMark;

    /**
     * 逻辑删除 0未删除1已删除
     */
    @TableLogic(value = "0",delval = "1")
    private Integer isDelete;

    private String empImg;
}
