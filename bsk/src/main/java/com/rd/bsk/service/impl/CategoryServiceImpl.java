package com.rd.bsk.service.impl;

import com.rd.bsk.pojo.Category;
import com.rd.bsk.mapper.CategoryMapper;
import com.rd.bsk.service.ICategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author liuhang
 * @since 2023-11-30
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements ICategoryService {

}
