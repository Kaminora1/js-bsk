package com.rd.bsk;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rd.bsk.mapper.EmpMapper;
import com.rd.bsk.pojo.Emp;
import com.rd.bsk.service.IEmpService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.xmlunit.util.Mapper;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

@SpringBootTest
@Slf4j
class BskApplicationTests {

    @Resource
    private IEmpService empService;

    @Test
    void testAdd(){

        log.info("e-------------empService={}",empService);
        Emp emp1=new Emp();
        emp1.setLoginName("1422275199");
        emp1.setLoginPwd("123456");
        emp1.setEmpName("liuhang");
        emp1.setEmpPhone("18071105292");
        emp1.setEmpSex("女");
        emp1.setEmpIdcard("420107200203060353");
        emp1.setEmpHiredate(LocalDateTime.now());
        emp1.setEmpSalary(new BigDecimal("5000"));
        emp1.setEmpRole(1);
        log.info("是否添加成功:{}",empService.save(emp1));
    }

    @Test
    void testGet1(){
        Emp emp = empService.getById(5);
        log.info("id为3的员工信息:{}",emp);
    }

    @Test
    void testGet2(){
        QueryWrapper<Emp> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("emp_phone","18062158752");
        Emp emp = empService.getOne(queryWrapper);
        log.info("id为3的员工信息:{}",emp);
    }

    @Test
    void testlist1(){
        List<Emp> list=empService.list();
        for(Emp emp:list){
            log.info("{}",emp);
        }
    }

    @Test
    void testlist2(){
        QueryWrapper<Emp> qw=new QueryWrapper<>();
        qw.eq("emp_sex","男");
        List<Emp> list=empService.list(qw);
        for(Emp emp:list){
            log.info("{}",emp);
        }
    }

    @Test
    void testUpdate(){
        Emp emp = empService.getById(5);
        if(emp!=null){
            emp.setEmpPhone("18062158753");
            log.info("是否修改成功:{}",empService.updateById(emp));
        }
        else{
            log.info("查无此id");
        }
    }

    @Test
    void testRemove(){
        Emp emp = empService.getById(5);
        log.info("删除成功成功:{}",empService.removeById(emp));
    }

    @Test
    void testCount(){
        QueryWrapper<Emp> qw=new QueryWrapper<>();
        qw.eq("emp_sex","女");
        log.info("女生个数有:{}",empService.count(qw));
    }

    @Test
    void testQuery(){
        QueryWrapper qw=new QueryWrapper<>();
        qw.ge("emp_salary",5000);
        List<Emp> list = empService.list(qw);
        for(Emp emp : list) {
            log.info("{}", emp);
        }
    }

    @Test//分组
    void testGroup() {
        QueryWrapper qw = new QueryWrapper<>();
        qw.select("emp_sex,count(id)");
        qw.groupBy("emp_sex");
        List<Map<String, Object>> list = empService.listMaps(qw);
        log.info("{}", list);
    }

    @Test//分页
    void testPage() {
        int pageNum=2;
        int pageSize=2;
        Page<Emp> page=new Page<>(pageNum,pageSize);
        Page<Emp> emppage=empService.page(page);
        long total = emppage.getTotal();
        long pages = emppage.getPages();
        log.info("总记录数:"+total);
        log.info("总页数:"+pages);
        List<Emp> records = emppage.getRecords();
        log.info("第{}页的数据:",pageNum);
        for(Emp emp:records){
            log.info("{}",emp);
        }
    }
}