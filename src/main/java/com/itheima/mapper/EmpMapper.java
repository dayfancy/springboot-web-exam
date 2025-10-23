package com.itheima.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itheima.dto.EmpListDTO;
import com.itheima.entity.Emp;
import com.itheima.vo.EmpListVO;
import org.apache.ibatis.annotations.*;

/**
 * 员工信息
 */
@Mapper
public interface EmpMapper extends BaseMapper<Emp> {

    Page<EmpListVO> selectEmpByPage(Page<EmpListDTO> page, EmpListDTO dto);
}
