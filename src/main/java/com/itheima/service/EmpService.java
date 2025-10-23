package com.itheima.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itheima.common.PageResult;
import com.itheima.dto.EmpListDTO;
import com.itheima.entity.Emp;
import com.itheima.entity.LoginInfo;
import com.itheima.vo.EmpListVO;
import com.itheima.vo.EmpSelectByIdVO;

public interface EmpService extends IService<Emp> {

    /**
     * 根据ID查询员工详细信息
     */
    EmpSelectByIdVO selectById(Integer id);

    PageResult<EmpListVO> selectEmpByPage(EmpListDTO dto);
    /**
     * 保存员工信息
     */
    void saveEmp(Emp emp);

    /**
     * 员工登录
     */
    LoginInfo login(Emp emp);

}
