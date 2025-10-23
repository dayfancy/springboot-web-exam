package com.itheima.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itheima.common.PageResult;
import com.itheima.dto.EmpListDTO;
import com.itheima.entity.Emp;
import com.itheima.entity.EmpExpr;
import com.itheima.entity.LoginInfo;
import com.itheima.exception.BusinessException;
import com.itheima.mapper.EmpExprMapper;
import com.itheima.mapper.EmpMapper;
import com.itheima.service.EmpService;
import com.itheima.utils.JwtUtils;
import com.itheima.vo.EmpListVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@SuppressWarnings("all")
public class EmpServiceImpl extends ServiceImpl<EmpMapper, Emp> implements EmpService {

    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private EmpExprMapper empExprMapper;

    @Override
    public PageResult<EmpListVO> selectEmpByPage(EmpListDTO dto) {
        Page<EmpListDTO> page = new Page<>(dto.getPage(), dto.getPageSize());
        Page<EmpListVO> empPage = empMapper.selectEmpByPage(page, dto);
        return new PageResult<>(empPage.getTotal(), empPage.getRecords());
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveEmp(Emp emp) {
        //1. 保存员工基本信息
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        emp.setPassword(DigestUtils.md5DigestAsHex((emp.getUsername() + "123").getBytes()));
        empMapper.insert(emp);

        //2. 保存员工工作经历信息
        List<EmpExpr> exprList = emp.getExprList();
        exprList.forEach(empExpr -> empExpr.setEmpId(emp.getId()));
        empExprMapper.insert(exprList);
    }






    @Override
    public LoginInfo login(Emp emp) {
        //1. 调用mapper接口, 根据用户名查询员工信息
        LambdaQueryWrapper<Emp> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Emp::getUsername, emp.getUsername());
        Emp e = empMapper.selectOne(wrapper);

        //2. 判断是否存在
        if(e == null){
            log.info("登录失败, 员工不存在");
            throw new BusinessException("员工不存在");
        }

        //3. 判断: 密码是否一致
        if(!emp.getPassword().equals(DigestUtils.md5DigestAsHex(e.getPassword().getBytes()))){
            log.info("登录失败, 密码错误");
            throw new BusinessException("密码错误");
        }

        log.info("登录成功, 员工信息: {}", e);
        //4. 生成JWT令牌, 返回
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", e.getId());
        claims.put("username", e.getUsername());
        String jwt = JwtUtils.generateToken(claims);
        return new LoginInfo(e.getId(), e.getUsername(), e.getName(), jwt);
    }
}
