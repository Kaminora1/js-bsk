package com.rd.bsk.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rd.bsk.pojo.Category;
import com.rd.bsk.pojo.Emp;
import com.rd.bsk.service.ICategoryService;
import com.rd.bsk.service.IEmpService;
import com.rd.bsk.util.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author liuhang
 * @since 2023-11-30
 */
@Slf4j
@RestController
@RequestMapping("/category")
public class CategoryController {
    @Resource
    private ICategoryService cateService;

    @PostMapping("/fenye")
    public ResultUtil fenye(@RequestParam(required = false,defaultValue = "1") Integer pageNum,
                            @RequestParam(required = false,defaultValue = "2") Integer pageSize){
        log.info("查询第{}页，每页显示{}条数据",pageNum,pageSize);
        Page<Category> page=new Page<>(pageNum,pageSize);
        Page<Category> CatePage=cateService.page(page);
        long total = CatePage.getTotal();
        long pages = CatePage.getPages();
        List<Category> records = CatePage.getRecords();
        return ResultUtil.success().put("total",total).put("pages",pages).put("records",records);
    }

    @PostMapping("/save")
    public ResultUtil saveCate(@RequestBody Category cate){
        log.info("参数:{}",cate);
        boolean flag = cateService.save(cate);
        if(flag){
            return ResultUtil.success("录入成功");
        }else{
            return ResultUtil.fail("录入失败");
        }
    }
}
