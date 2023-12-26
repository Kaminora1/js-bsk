package com.rd.bsk.pojo;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author liuhang
 * @since 2023-11-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_category")
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 分类编号
     */
    private Integer id;

    /**
     * 分类名称
     */
    private String typeName;

    /**
     * 分类排序值
     */
    private Integer typeSort;

    /**
     * 逻辑删除 0 未删除 1 已删除
     */
    @TableLogic(value = "0",delval = "1")
    private Integer isDelete;


}
