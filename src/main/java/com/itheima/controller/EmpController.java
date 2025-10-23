package com.itheima.controller;

import com.itheima.common.PageResult;
import com.itheima.common.Result;
import com.itheima.dto.EmpListDTO;
import com.itheima.dto.EmpUpdateDTO;
import com.itheima.entity.Emp;
import com.itheima.service.EmpService;
import com.itheima.vo.EmpListVO;
import com.itheima.vo.EmpSelectByIdVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PutMapping
    public Result updateEmp(@RequestBody EmpUpdateDTO dto) {
        empService.updateEmp(dto);
        return Result.success();
    }
    /**
     * 批量删除员工
     */
    @DeleteMapping("/{ids}")
    public Result deleteByIds(@PathVariable List<Integer> ids) {
        empService.deleteByIds(ids);
        return Result.success();
    }

    /**
     * 根据ID查询员工详细信息
     */
    @GetMapping("/{id}")
    public Result selectById(@PathVariable Integer id) {
      EmpSelectByIdVO vo = empService.selectById(id);
      return Result.success(vo);
    }


    /**
     * 员工列表查询
     */
    @GetMapping
    public Result selectEmpByPage(EmpListDTO dto) {
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
