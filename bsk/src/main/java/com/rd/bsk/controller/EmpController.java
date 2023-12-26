package com.rd.bsk.controller;


import cn.hutool.http.server.HttpServerRequest;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rd.bsk.pojo.Emp;
import com.rd.bsk.service.IEmpService;
import com.rd.bsk.util.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.LifecycleState;
import org.apache.commons.io.FilenameUtils;
import org.apache.ibatis.annotations.Result;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author liuhang
 * @since 2023-11-21
 */

@Slf4j
@RestController//将这个类标记为控制器，并且该类中方法返回是json格式的数据
@RequestMapping("/emp")//表示类中的方法都是以这个地址/emp作为父路径
public class EmpController {
    @Resource
    private IEmpService empService;

    @PostMapping("/login")
    public ResultUtil login(@RequestBody Emp emp){
        log.info("参数:{}",emp);
        QueryWrapper<Emp> qw=new QueryWrapper<>();
        qw.eq("login_name",emp.getLoginName())
                .eq("login_pwd",emp.getLoginPwd());
        Emp empInfo = empService.getOne(qw);
        if(Objects.isNull(empInfo)){
            return ResultUtil.fail("账户或密码错误！");
        }else{
            return ResultUtil.success("登录成功").put("empInfo",empInfo);
        }
    }
    
    @GetMapping("/list")
    public ResultUtil getEmpList(){
        List<Emp> list = empService.list();
        return ResultUtil.success("员工列表查询成功！").put("empList",list);
    }


//    @GetMapping("/list/{id}")
//    public ResultUtil getEmpById(@PathVariable("id")Integer id)

    @PostMapping ("/fenye")
    public ResultUtil fenye(@RequestParam(required = false,defaultValue = "1") Integer pageNum,
                            @RequestParam(required = false,defaultValue = "2") Integer pageSize){
        log.info("查询第{}页，每页显示{}条数据",pageNum,pageSize);
        Page<Emp> page=new Page<>(pageNum,pageSize);
        Page<Emp> empPage=empService.page(page);
        long total = empPage.getTotal();
        long pages = empPage.getPages();
        List<Emp> records = empPage.getRecords();
        return ResultUtil.success().put("total",total).put("pages",pages).put("records",records);
    }

    @GetMapping("/checkIdCard/{idcard}")
    public ResultUtil checkIdCard(@PathVariable("empIdcard") String idcard){
        log.info("检查empIdCard={}",idcard);
        QueryWrapper<Emp> qw =new QueryWrapper<>();
        qw.eq("empIdcard",idcard);
        Emp emp = empService.getOne(qw);
        if(emp == null){
            return ResultUtil.success("可以使用!");
        }else{
            return ResultUtil.fail("此此身份证已被绑定!");
        }
    }

    @GetMapping("/checkEmpPhone/{empPhone}")
    public ResultUtil checkUserPhone(@PathVariable("empPhone") String empPhone)
    {
        log.info("检查:{}", empPhone);
        QueryWrapper<Emp> queryWrapper =
                new QueryWrapper<>();
        queryWrapper.eq("empPhone",empPhone);
        Emp emp = empService.getOne(queryWrapper);
        return emp == null ?
                ResultUtil.success("此手机号码可以使用!") :
                ResultUtil.fail("此手机号码已经被注册！");
    }

    @GetMapping("/checkLoginName/{name}")
    public ResultUtil checkLoginName(@PathVariable("loginName") String name){
        log.info("检查loginName={}",name);
        QueryWrapper<Emp> qw =new QueryWrapper<>();
        qw.eq("loginName",name);
        Emp emp = empService.getOne(qw);
        if(emp == null){
            return ResultUtil.success("可以使用!");
        }else{
            return ResultUtil.fail("此登录名称已被使用!");
        }
    }

    @PostMapping("/save")
    public ResultUtil saveEmp(@RequestBody Emp emp){
        log.info("参数:{}",emp);
        boolean flag = empService.save(emp);
        if(flag){
            return ResultUtil.success("录入成功");
        }else{
            return ResultUtil.fail("录入失败");
        }
    }

    @GetMapping("/getCurrentUser")
    public ResultUtil getCurrentUser(HttpSession session){
        Object user = session.getAttribute("user");
        if(user==null){
            return ResultUtil.fail("session已过期请求重新登录");
        }else{
            Emp emp = (Emp) user;
            List<Emp> list = new ArrayList<>();
            list.add(emp);

            return  ResultUtil.success("success").put("list",list);
        }
    }

    @PostMapping("/upload")
    public ResultUtil  upload(@RequestBody MultipartFile img, HttpServletRequest req) throws IOException{
        String dir = "/upload";
        String realPath = req.getServletContext().getRealPath(dir);
        File file = new File(realPath);
        if(!file.exists()){
            file.mkdir();
        }
        //文件改名
        String newName = UUID.randomUUID().toString();
        String oldName = img.getOriginalFilename();

        //获取原生文件后缀名
        String suffix= "."+ FilenameUtils.getPrefix(oldName);

        //生成新文件名
        newName = newName+suffix;
        img.transferTo(new File(realPath+newName));

        Object user =req.getSession().getAttribute("User");
        Emp emp = (Emp)user;
        emp.setEmpImg(newName);
        empService.updateById(emp);

        return  ResultUtil.success("图片上传成功").put("imgName",newName);
    }
}


