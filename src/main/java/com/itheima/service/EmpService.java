package com.itheima.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itheima.common.PageResult;
import com.itheima.dto.EmpListDTO;
import com.itheima.entity.Emp;
import com.itheima.entity.LoginInfo;
import com.itheima.vo.EmpListVO;

public interface EmpService extends IService<Emp> {

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
