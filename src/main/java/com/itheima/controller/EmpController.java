package com.itheima.controller;

import com.itheima.common.PageResult;
import com.itheima.common.Result;
import com.itheima.dto.EmpListDTO;
import com.itheima.entity.Emp;
import com.itheima.service.EmpService;
import com.itheima.vo.EmpListVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 员工管理Controller
 */
@Slf4j
@RestController
@RequestMapping("/emps")
@SuppressWarnings("all")
public class EmpController {

    @Autowired
    private EmpService empService;


    /**
     * 员工列表查询
     */
    @GetMapping
    public Result selectEmpByPage(EmpListDTO dto){
      PageResult<EmpListVO> pageResult = empService.selectEmpByPage(dto);
      return Result.success(pageResult);
    }
    /**
     * 新增员工
     */
    @PostMapping
    public Result save(@RequestBody Emp emp) {
        log.info("新增员工: {}", emp);
        empService.saveEmp(emp);
        return Result.success();
    }

}
