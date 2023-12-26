package com.rd.bsk.service.impl;

import com.rd.bsk.pojo.Emp;
import com.rd.bsk.mapper.EmpMapper;
import com.rd.bsk.service.IEmpService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author liuhang
 * @since 2023-11-21
 */
@Service
public class EmpServiceImpl extends ServiceImpl<EmpMapper, Emp> implements IEmpService {

}
